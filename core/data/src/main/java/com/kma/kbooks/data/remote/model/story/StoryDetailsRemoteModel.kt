package com.kma.kbooks.data.remote.model.story

import com.kma.kbooks.data.remote.model.GenreRemoteModel
import com.kma.kbooks.data.remote.model.asEntity
import com.kma.kbooks.domain.data.model.story.StoryDetails
import java.util.Date

data class StoryDetailsRemoteModel(
    val storyId: Int?,

    val title: String?,

    val author: String?,

    val thumbnail: String?,

    val summary: String?,

    val view: Int?,

    val rating: Float?,

    val status: String?,

    val genres: List<GenreRemoteModel>?,

    val publishedAt: Date?
)

internal fun StoryDetailsRemoteModel.asEntity(): StoryDetails = StoryDetails(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail,
    summary = this.summary,
    view = this.view,
    rating = this.rating,
    status = this.status,
    genres = this.genres?.map { it.asEntity() },
    publishedAt = this.publishedAt
)
