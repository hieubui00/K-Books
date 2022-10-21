package com.kma.kbooks.domain.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val chapterId: Int = -1,

    val name: String?
) : Parcelable

data class ChapterDetails(
    val chapterId: Int = -1,

    val name: String?,

    val content: String?
)
