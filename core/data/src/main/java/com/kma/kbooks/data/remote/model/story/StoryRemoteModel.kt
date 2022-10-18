package com.kma.kbooks.data.remote.model.story

import com.kma.kbooks.domain.data.model.Story

class StoryRemoteModel {
    val storyId: Int? = null

    val title: String? = null

    val thumbnail: String? = null

    val author: String? = null
}

internal fun StoryRemoteModel.asEntity(): Story = Story(
    storyId = this.storyId ?: -1,
    title = this.title,
    author = this.author,
    thumbnail = this.thumbnail
)
