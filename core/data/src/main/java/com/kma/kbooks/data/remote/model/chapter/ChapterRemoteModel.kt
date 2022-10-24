package com.kma.kbooks.data.remote.model.chapter

import com.kma.kbooks.data.local.model.ChapterLocalModel
import com.kma.kbooks.domain.data.model.Chapter

data class ChapterRemoteModel(
    val chapterId: Int?,

    val name: String?
)

internal fun ChapterRemoteModel.asEntity(): Chapter = Chapter(
    chapterId = this.chapterId ?: -1,
    name = this.name
)

internal fun ChapterRemoteModel.asLocalModel(storyId: Int): ChapterLocalModel = ChapterLocalModel(
    chapterId = this.chapterId ?: -1,
    name = this.name,
    content = null,
    storyId = storyId
)
