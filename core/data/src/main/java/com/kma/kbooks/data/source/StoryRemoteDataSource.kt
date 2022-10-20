package com.kma.kbooks.data.source

import com.kma.kbooks.data.remote.model.chapter.ChapterRemoteModel
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
        page: Int? = -1
    ): List<StoryRemoteModel>

    suspend fun getStoryDetails(storyId: Int): StoryDetailsRemoteModel?

    suspend fun getStoryChapters(
        storyId: Int,
        page: Int? = -1
    ): List<ChapterRemoteModel>
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

    override suspend fun getStoryChapters(
        storyId: Int,
        page: Int?
    ): List<ChapterRemoteModel> = withContext(ioDispatcher) {
        val response = kBooksService.getStoryChapters(storyId, page)

        return@withContext response.data ?: emptyList()
    }
}
