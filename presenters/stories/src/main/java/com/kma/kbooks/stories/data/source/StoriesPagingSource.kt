package com.kma.kbooks.stories.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
import com.kma.kbooks.stories.injection.scope.StoriesScope
import timber.log.Timber
import javax.inject.Inject

@StoriesScope
class StoriesPagingSource @Inject constructor(
    private val storyRepository: StoryRepository
) : PagingSource<Int, Story>() {
    var status: Array<out Status>? = null

    var sort: Pair<SortBy, SortOrder>? = null

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> = try {
        val page = params.key ?: 1
        val stories = storyRepository.getStories(
            status = status ?: emptyArray(),
            sort = sort,
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
