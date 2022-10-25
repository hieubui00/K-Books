package com.kma.kbooks.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.model.StoryDetails
import java.util.Date

@Entity(tableName = "stories")
data class StoryLocalModel(
    @PrimaryKey
    val storyId: Int,

    val title: String?,

    val author: String?,

    val thumbnail: String?,

    val summary: String?,

    val view: Int?,

    val rating: Float?,

    val status: String?,

    val publishedAt: Date?
)

internal fun StoryLocalModel.asEntity(): Story = Story(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail
)

internal fun StoryLocalModel.asDetailsEntity(): StoryDetails = StoryDetails(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail,
    summary = this.summary,
    view = this.view,
    rating = this.rating,
    status = this.status,
    genres = emptyList(),
    publishedAt = this.publishedAt,
    isFavourite = true,
)

internal fun StoryDetails.asLocalModel(): StoryLocalModel = StoryLocalModel(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail,
    summary = this.summary,
    view = this.view,
    rating = this.rating,
    status = this.status,
    publishedAt = this.publishedAt
)
