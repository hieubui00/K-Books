package com.kma.kbooks.data.source

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kma.kbooks.data.remote.model.StoryRemoteModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface StoryRemoteDataSource {

    suspend fun getHotStories(): List<StoryRemoteModel>
}

class StoryRemoteDataSourceImpl @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : StoryRemoteDataSource {
    private val firestore = Firebase.firestore

    override suspend fun getHotStories(): List<StoryRemoteModel> = withContext(ioDispatcher) {
        firestore.collection(COLLECTION_STORIES)
            .orderBy(FIELD_VIEW, Query.Direction.DESCENDING)
            .get()
            .await()
            .toObjects(StoryRemoteModel::class.java)
    }

    companion object {
        private const val COLLECTION_STORIES = "stories"

        private const val FIELD_VIEW = "view"
    }
}