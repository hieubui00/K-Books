package com.kma.kbooks.domain.data.model.story

import com.kma.kbooks.domain.data.model.Genre
import java.util.*

data class StoryDetails(
    val storyId: Int?,

    val title: String?,

    val author: String?,

    val thumbnail: String?,

    val summary: String?,

    val view: Int?,

    val rating: Float?,

    val status: String?,

    val genres: List<Genre>?,

    val publishedAt: Date?
)