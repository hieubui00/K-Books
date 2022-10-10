package com.kma.kbooks.data.repository

import com.kma.kbooks.data.remote.model.asEntity
import com.kma.kbooks.data.source.StoryRemoteDataSource
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyRemoteDataSource: StoryRemoteDataSource
) : StoryRepository {

    override suspend fun getHotStories(page: Int): List<Story> {
        return storyRemoteDataSource.getHotStories().map { it.asEntity() }
    }

    override suspend fun getNewUpdatedStories(page: Int): List<Story> {
        return storyRemoteDataSource.getNewUpdatedStories().map { it.asEntity() }
    }

    override suspend fun getCompletedStories(page: Int): List<Story> {
        return storyRemoteDataSource.getCompletedStories().map { it.asEntity() }
    }
}