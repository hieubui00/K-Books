package com.kma.kbooks.domain.data.repository

import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder

interface StoryRepository {

    suspend fun getStories(
        vararg status: Status,
        sort: Pair<SortBy, SortOrder>? = null,
        page: Int? = 1
    ): List<Story>

    suspend fun getStoryDetails(storyId: Int): StoryDetails?
}
