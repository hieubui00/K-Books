package com.kma.kbooks.data.repository

import com.kma.kbooks.data.remote.model.chapter.asEntity
import com.kma.kbooks.data.source.ChapterRemoteDataSource
import com.kma.kbooks.domain.data.model.ChapterDetails
import com.kma.kbooks.domain.data.repository.ChapterRepository
import javax.inject.Inject

class ChapterRepositoryImpl @Inject constructor(
    private val chapterRemoteDataSource: ChapterRemoteDataSource
) : ChapterRepository {

    override suspend fun getChapterDetails(chapterId: Int): ChapterDetails? {
        return chapterRemoteDataSource.getChapterDetails(chapterId)?.asEntity()
    }
}