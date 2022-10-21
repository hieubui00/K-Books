package com.kma.kbooks.domain.data.repository

import com.kma.kbooks.domain.data.model.ChapterDetails

interface ChapterRepository {

    suspend fun getChapterDetails(chapterId: Int): ChapterDetails?
}
