package com.kma.kbooks.data.local.source

import com.kma.kbooks.data.local.dao.ChapterDao
import com.kma.kbooks.data.local.model.ChapterLocalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface ChapterLocalDataSource {

    suspend fun getChapters(storyId: Int, page: Int = 1): List<ChapterLocalModel>

    suspend fun getChapter(chapterId: Int): ChapterLocalModel?

    suspend fun saveChapter(vararg chapters: ChapterLocalModel)

    suspend fun updateChapter(chapterLocalModel: ChapterLocalModel)
}

class ChapterLocalDataSourceImpl @Inject constructor(
    private val chapterDao: ChapterDao,

    @Named("io")
    private val ioDispatcher: CoroutineDispatcher
) : ChapterLocalDataSource {

    override suspend fun getChapters(
        storyId: Int,
        page: Int
    ): List<ChapterLocalModel> = withContext(ioDispatcher) {
        val limit = 16
        val offset = limit * (page - 1)

        return@withContext chapterDao.getChapterByStoryId(storyId, limit, offset)
    }

    override suspend fun getChapter(chapterId: Int): ChapterLocalModel? = withContext(ioDispatcher) {
        return@withContext chapterDao.getChapter(chapterId)
    }

    override suspend fun saveChapter(vararg chapters: ChapterLocalModel) = withContext(ioDispatcher) {
        chapterDao.insertChapter(*chapters)
    }

    override suspend fun updateChapter(chapterLocalModel: ChapterLocalModel) = withContext(ioDispatcher) {
        chapterDao.updateChapter(chapterLocalModel)
    }
}