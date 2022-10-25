package com.kma.kbooks.dashboard.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kma.kbooks.dashboard.injection.scope.DashboardScope
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import timber.log.Timber
import javax.inject.Inject

@DashboardScope
class LocalStoriesPagingSource @Inject constructor(
    private val storyRepository: StoryRepository
) : PagingSource<Int, Story>() {

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> = try {
        val page = params.key ?: 1
        val stories = storyRepository.getLocalStories(page = page)

        LoadResult.Page(
            data = stories,
            prevKey = null,
            nextKey = page.takeIf { stories.isNotEmpty() }?.plus(1)
        )
    } catch (e: Exception) {
        Timber.e(e)
        LoadResult.Error(e)
    }
}
