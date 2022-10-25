package com.kma.kbooks.data.remote.source

import com.kma.kbooks.data.remote.model.chapter.ChapterDetailsRemoteModel
import com.kma.kbooks.data.remote.request.KBooksService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface ChapterRemoteDataSource {

    suspend fun getChapterDetails(chapterId: Int): ChapterDetailsRemoteModel?
}

class ChapterRemoteDataSourceImpl @Inject constructor(
    private val kBooksService: KBooksService,

    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ChapterRemoteDataSource {

    override suspend fun getChapterDetails(
        chapterId: Int
    ): ChapterDetailsRemoteModel? = withContext(ioDispatcher) {
        val response = kBooksService.getChapterDetails(chapterId)

        return@withContext response.data
    }
}
