package com.kma.kbooks.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.model.ChapterDetails

@Entity(tableName = "chapters")
data class ChapterLocalModel(
    @PrimaryKey
    val chapterId: Int,

    val name: String?,

    val content: String?,

    val storyId: Int
)

internal fun ChapterLocalModel.asEntity(): Chapter = Chapter(
    chapterId = this.chapterId,
    name = this.name
)

internal fun ChapterLocalModel.asDetailsEntity(): ChapterDetails = ChapterDetails(
    chapterId = this.chapterId,
    name = this.name,
    content = this.content
)