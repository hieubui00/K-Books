package com.kma.kbooks.data.remote.model

import com.google.firebase.firestore.DocumentId
import com.kma.kbooks.domain.data.model.Story

data class StoryRemoteModel(
    @DocumentId
    val storyId: String?,

    val title: String?,

    val thumbnail: String?,

    val author: String?
)

internal fun StoryRemoteModel.asEntity(): Story = Story(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail
)
