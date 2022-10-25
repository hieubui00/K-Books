package com.kma.kbooks.data.remote.model.chapter

import com.kma.kbooks.data.local.model.ChapterLocalModel
import com.kma.kbooks.domain.data.model.ChapterDetails

data class ChapterDetailsRemoteModel(
    val chapterId: Int?,

    val name: String?,

    val content: String?,

    val storyId: Int?
)

internal fun ChapterDetailsRemoteModel.asEntity(): ChapterDetails = ChapterDetails(
    chapterId = this.chapterId ?: -1,
    name = this.name,
    content = this.content,
    storyId = this.storyId ?: -1
)

internal fun ChapterDetailsRemoteModel.asLocalModel(): ChapterLocalModel = ChapterLocalModel(
    chapterId = this.chapterId ?: -1,
    name = this.name,
    content = this.content,
    storyId = this.storyId ?: -1
)
