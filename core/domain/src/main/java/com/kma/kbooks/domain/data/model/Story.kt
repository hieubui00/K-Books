package com.kma.kbooks.domain.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

data class Story(
    val storyId: Int = -1,

    val title: String?,

    val author: String?,

    val thumbnail: String?
)

@Parcelize
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

    val publishedAt: Date?,

    val isFavourite: Boolean = false
) : Parcelable

enum class Status {
    IMCOMING, COMPLETED
}
