package com.kma.kbooks.domain.data.repository

import com.kma.kbooks.domain.data.model.Story

interface StoryRepository {

    suspend fun getHotStories(page: Int = 1): List<Story>

    suspend fun getNewUpdatedStories(page: Int = 1): List<Story>

    suspend fun getCompletedStories(page: Int = 1): List<Story>
}
