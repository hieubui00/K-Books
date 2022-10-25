package com.kma.kbooks.domain.data.repository

import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder

interface StoryRepository {

    suspend fun getStories(
        query: String? = null,
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>? = null,
        page: Int? = 1
    ): List<Story>

    suspend fun getLocalStories(page: Int = 1): List<Story>

    suspend fun getStoryDetails(storyId: Int): StoryDetails?

    suspend fun getStoryChapters(storyId: Int, page: Int = 1): List<Chapter>

    suspend fun saveStory(vararg storyDetails: StoryDetails)

    suspend fun deleteStory(storyDetails: StoryDetails)
}
