package com.kma.kbooks.search.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.search.injection.scope.SearchScope
import timber.log.Timber
import javax.inject.Inject

@SearchScope
class StoriesPagingSource @Inject constructor(
    private val storyRepository: StoryRepository
) : PagingSource<Int, Story>() {
    var query: String? = null

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> = try {
        val page = params.key ?: 1
        val stories = storyRepository.getStories(
            query = query,
            page = page
        )

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
