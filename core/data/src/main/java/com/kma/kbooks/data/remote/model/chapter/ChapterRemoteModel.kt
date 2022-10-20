package com.kma.kbooks.data.remote.model.chapter

import com.kma.kbooks.domain.data.model.Chapter

data class ChapterRemoteModel(
    val chapterId: Int?,

    val name: String?
)

internal fun ChapterRemoteModel.asEntity(): Chapter = Chapter(
    chapterId = this.chapterId ?: -1,
    name = this.name
)
