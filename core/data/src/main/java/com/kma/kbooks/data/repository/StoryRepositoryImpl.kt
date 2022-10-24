package com.kma.kbooks.data.repository

import com.kma.kbooks.data.local.model.asDetailsEntity
import com.kma.kbooks.data.local.model.asEntity
import com.kma.kbooks.data.local.model.asLocalModel
import com.kma.kbooks.data.local.source.ChapterLocalDataSource
import com.kma.kbooks.data.local.source.StoryLocalDataSource
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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyRemoteDataSource: StoryRemoteDataSource,

    private val storyLocalDataSource: StoryLocalDataSource,

    private val chapterLocalDataSource: ChapterLocalDataSource,
) : StoryRepository {

    override suspend fun getStories(
        query: String?,
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>?,
        page: Int?
    ): List<Story> = try {
        storyRemoteDataSource.getStories(
            query = query,
            status = status,
            sort = sort,
            page = page
        ).map { it.asEntity() }
    } catch (e: Exception) {
        Timber.e(e)
        emptyList()
    }

    override suspend fun getLocalStories(page: Int): List<Story> {
        return storyLocalDataSource.getStories(page).map { it.asEntity() }
    }

    override suspend fun getStoryDetails(storyId: Int): StoryDetails? = coroutineScope {
        val story = async { storyLocalDataSource.getStory(storyId) }
        try {
            val isFavourite = story.await() != null

            storyRemoteDataSource.getStoryDetails(storyId)?.asEntity(isFavourite)
        } catch (e: Exception) {
            Timber.e(e)
            story.await()?.asDetailsEntity()
        }
    }

    override suspend fun getStoryChapters(storyId: Int, page: Int): List<Chapter> = try {
        val chapters = storyRemoteDataSource.getStoryChapters(storyId, page)
        val data = chapters.map { it.asLocalModel(storyId) }.toTypedArray()

        chapterLocalDataSource.saveChapter(*data)
        chapters.map { it.asEntity() }
    } catch (e: Exception) {
        Timber.e(e)
        chapterLocalDataSource.getChapters(storyId, page).map { it.asEntity() }
    }

    override suspend fun saveStory(vararg storyDetails: StoryDetails) {
        storyLocalDataSource.saveStory(*storyDetails.map { it.asLocalModel() }.toTypedArray())
    }

    override suspend fun deleteStory(storyDetails: StoryDetails) {
        storyLocalDataSource.removeStory(storyDetails.asLocalModel())
    }
}
