package com.kma.kbooks.data.local.source

import com.kma.kbooks.data.local.dao.StoryDao
import com.kma.kbooks.data.local.model.StoryLocalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface StoryLocalDataSource {

    suspend fun getStories(): List<StoryLocalModel>

    suspend fun getStory(storyId: Int): StoryLocalModel?

    suspend fun saveStory(vararg stories: StoryLocalModel)
}

class StoryLocalDataSourceImpl @Inject constructor(
    private val storyDao: StoryDao,

    @Named("io")
    private val ioDispatcher: CoroutineDispatcher
) : StoryLocalDataSource {

    override suspend fun getStories(): List<StoryLocalModel> = withContext(ioDispatcher) {
        return@withContext storyDao.getStories()
    }

    override suspend fun getStory(storyId: Int): StoryLocalModel? = withContext(ioDispatcher){
        return@withContext storyDao.getStory(storyId)
    }

    override suspend fun saveStory(vararg stories: StoryLocalModel) = withContext(ioDispatcher) {
        storyDao.insertStory(*stories)
    }
}