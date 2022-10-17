package com.kma.kbooks.data.source

import com.kma.kbooks.data.remote.model.story.StoryDetailsRemoteModel
import com.kma.kbooks.data.remote.model.story.StoryRemoteModel
import com.kma.kbooks.data.remote.request.KBooksService
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface StoryRemoteDataSource {

    suspend fun getStories(
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>? = null,
        page: Int?
    ): List<StoryRemoteModel>

    suspend fun getStoryDetails(storyId: Int): StoryDetailsRemoteModel?
}

class StoryRemoteDataSourceImpl @Inject constructor(
    private val kBooksService: KBooksService,

    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : StoryRemoteDataSource {

    override suspend fun getStories(
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>?,
        page: Int?
    ): List<StoryRemoteModel> = withContext(ioDispatcher) {
        val response = kBooksService.getStories(
            status = status.takeIf { it.isNotEmpty() }?.joinToString(",")?.lowercase(),
            sort = sort?.let { "${sort.first}.${sort.second}".lowercase() },
            page = page
        )

        return@withContext response.data ?: emptyList()
    }

    override suspend fun getStoryDetails(
        storyId: Int
    ): StoryDetailsRemoteModel? = withContext(ioDispatcher) {
        val response = kBooksService.getStoryDetails(storyId)

        return@withContext response.data
    }
}
