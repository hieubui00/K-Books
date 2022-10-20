package com.kma.kbooks.data.repository

import com.kma.kbooks.data.remote.model.chapter.asEntity
import com.kma.kbooks.data.remote.model.story.asEntity
import com.kma.kbooks.data.source.StoryRemoteDataSource
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyRemoteDataSource: StoryRemoteDataSource
) : StoryRepository {

    override suspend fun getStories(
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>?,
        page: Int?
    ): List<Story> = storyRemoteDataSource.getStories(
        *status,
        sort = sort,
        page = page
    ).map { it.asEntity() }

    override suspend fun getStoryDetails(storyId: Int): StoryDetails? {
        return storyRemoteDataSource.getStoryDetails(storyId)?.asEntity()
    }

    override suspend fun getStoryChapters(storyId: Int, page: Int?): List<Chapter> {
        return storyRemoteDataSource.getStoryChapters(storyId, page).map { it.asEntity() }
    }
}
