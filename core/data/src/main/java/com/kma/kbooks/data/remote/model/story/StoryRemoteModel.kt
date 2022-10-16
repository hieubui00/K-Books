package com.kma.kbooks.data.remote.model.story

import com.google.firebase.firestore.DocumentId
import com.kma.kbooks.domain.data.model.Story

class StoryRemoteModel {
    @DocumentId
    val storyId: String? = null

    val title: String? = null

    val thumbnail: String? = null

    val author: String? = null
}

internal fun StoryRemoteModel.asEntity(): Story = Story(
    storyId = this.storyId,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail
)
