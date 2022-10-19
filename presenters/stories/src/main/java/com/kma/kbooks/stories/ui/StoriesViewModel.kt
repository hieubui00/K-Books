package com.kma.kbooks.stories.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
import com.kma.kbooks.stories.data.source.StoriesPagingSource
import com.kma.kbooks.stories.injection.scope.StoriesScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@StoriesScope
class StoriesViewModel @Inject constructor(
    state: SavedStateHandle,

    private val storiesPagingSource: StoriesPagingSource
) : ViewModel() {
    val stories: Flow<PagingData<Story>>

    init {
        val sortBy = state.get<SortBy>("sortBy") ?: SortBy.VIEW
        val sortOrder = state.get<SortOrder>("sortOrder") ?: SortOrder.DESC
        val pagingConfig = PagingConfig(pageSize = 16)

        storiesPagingSource.status = state.get<String>("status")?.let { arrayOf(Status.valueOf(it)) }
        storiesPagingSource.sort = sortBy to sortOrder
        stories = Pager(pagingConfig) { storiesPagingSource }
            .liveData
            .cachedIn(viewModelScope)
            .asFlow()
    }
}