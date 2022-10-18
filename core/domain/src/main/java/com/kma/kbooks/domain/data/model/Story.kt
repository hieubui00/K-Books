package com.kma.kbooks.domain.data.model

import java.util.Date

data class Story(
    val storyId: Int = -1,

    val title: String?,

    val author: String?,

    val thumbnail: String?
)

data class StoryDetails(
    val storyId: Int = -1,

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

enum class Status {
    IMCOMING, COMPLETED
}
