package com.kma.kbooks.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.search.data.source.StoriesPagingSource
import com.kma.kbooks.search.injection.scope.SearchScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@SearchScope
class SearchViewModel @Inject constructor(
    private val storiesPagingSource: StoriesPagingSource
) : ViewModel() {
    private val queryChannel = Channel<String>(capacity = Channel.CONFLATED)

    val stories: Flow<PagingData<Story>>

    init {
        val pagingConfig = PagingConfig(pageSize = 16)

        stories = queryChannel
            .consumeAsFlow()
            .debounce(300)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .flatMapLatest {
                storiesPagingSource.query = it
                Pager(pagingConfig) { storiesPagingSource }
                    .liveData
                    .cachedIn(viewModelScope)
                    .asFlow()
            }
    }

    fun setQuery(query: String): Job = viewModelScope.launch {
        queryChannel.send(query)
    }
}