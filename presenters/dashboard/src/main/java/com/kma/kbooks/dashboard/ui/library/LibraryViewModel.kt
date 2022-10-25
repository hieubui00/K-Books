package com.kma.kbooks.dashboard.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kma.kbooks.dashboard.data.source.LocalStoriesPagingSource
import com.kma.kbooks.dashboard.injection.scope.DashboardScope
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@DashboardScope
class LibraryViewModel @Inject constructor(
    private val storyRepository: StoryRepository,
) : ViewModel() {
    val stories: Flow<PagingData<Story>>

    init {
        val pagingConfig = PagingConfig(pageSize = 16)

        stories = Pager(pagingConfig) { LocalStoriesPagingSource(storyRepository) }
            .liveData
            .cachedIn(viewModelScope)
            .asFlow()
    }
}
