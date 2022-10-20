package com.kma.kbooks.chapters.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kma.kbooks.chapters.injection.scope.ChaptersScope
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.repository.StoryRepository
import timber.log.Timber
import javax.inject.Inject

@ChaptersScope
class ChaptersPagingSource @Inject constructor(
    private val storyRepository: StoryRepository
) : PagingSource<Int, Chapter>() {
    var storyId: Int = -1

    override fun getRefreshKey(state: PagingState<Int, Chapter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Chapter> = try {
        val page = params.key ?: 1
        val chapters = storyRepository.getStoryChapters(
            storyId = storyId,
            page = page
        )

        LoadResult.Page(
            data = chapters,
            prevKey = null,
            nextKey = page.takeIf { chapters.isNotEmpty() }?.plus(1)
        )
    } catch (e: Exception) {
        Timber.e(e)
        LoadResult.Error(e)
    }
}
