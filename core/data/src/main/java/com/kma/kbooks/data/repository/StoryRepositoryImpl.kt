package com.kma.kbooks.data.repository

import com.kma.kbooks.data.local.model.asEntity
import com.kma.kbooks.data.local.source.ChapterLocalDataSource
import com.kma.kbooks.data.remote.model.chapter.asEntity
import com.kma.kbooks.data.remote.model.chapter.asLocalModel
import com.kma.kbooks.data.remote.model.story.asEntity
import com.kma.kbooks.data.remote.source.StoryRemoteDataSource
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
import timber.log.Timber
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyRemoteDataSource: StoryRemoteDataSource,

    private val storyLocalDataSource: ChapterLocalDataSource,
) : StoryRepository {

    override suspend fun getStories(
        query: String?,
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>?,
        page: Int?
    ): List<Story> = storyRemoteDataSource.getStories(
        query = query,
        status = status,
        sort = sort,
        page = page
    ).map { it.asEntity() }

    override suspend fun getStoryDetails(storyId: Int): StoryDetails? {
        return storyRemoteDataSource.getStoryDetails(storyId)?.asEntity()
    }

    override suspend fun getStoryChapters(storyId: Int, page: Int): List<Chapter> = try {
        val chapters = storyRemoteDataSource.getStoryChapters(storyId, page)
        val data = chapters.map { it.asLocalModel(storyId) }.toTypedArray()

        storyLocalDataSource.saveChapter(*data)
        chapters.map { it.asEntity() }
    } catch (e: Exception) {
        Timber.e(e)
        storyLocalDataSource.getChapters(storyId, page).map { it.asEntity() }
    }
}
