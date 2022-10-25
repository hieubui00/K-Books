package com.kma.kbooks.data.repository

import com.kma.kbooks.data.local.model.asDetailsEntity
import com.kma.kbooks.data.local.source.ChapterLocalDataSource
import com.kma.kbooks.data.remote.model.chapter.asEntity
import com.kma.kbooks.data.remote.model.chapter.asLocalModel
import com.kma.kbooks.data.remote.source.ChapterRemoteDataSource
import com.kma.kbooks.domain.data.model.ChapterDetails
import com.kma.kbooks.domain.data.repository.ChapterRepository
import timber.log.Timber
import javax.inject.Inject

class ChapterRepositoryImpl @Inject constructor(
    private val chapterRemoteDataSource: ChapterRemoteDataSource,

    private val chapterLocalDataSource: ChapterLocalDataSource,
) : ChapterRepository {

    override suspend fun getChapterDetails(chapterId: Int): ChapterDetails? = try {
        val chapterDetails = chapterRemoteDataSource.getChapterDetails(chapterId)

        chapterDetails?.apply { chapterLocalDataSource.updateChapter(asLocalModel()) }?.asEntity()
    } catch (e: Exception) {
        Timber.e(e)
        chapterLocalDataSource.getChapter(chapterId)?.asDetailsEntity()
    }
}
