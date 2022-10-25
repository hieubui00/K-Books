package com.kma.kbooks.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.search.data.source.StoriesPagingSource
import com.kma.kbooks.search.injection.scope.SearchScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@SearchScope
class SearchViewModel @Inject constructor(
    private val storiesPagingSource: StoriesPagingSource
) : ViewModel() {
    private val _query = MutableLiveData<String>()

    val stories: Flow<PagingData<Story>>

    init {
        stories = _query
            .asFlow()
            .debounce(300)
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .distinctUntilChanged()
            .flatMapLatest {
                val pagingConfig = PagingConfig(pageSize = 16)

                Pager(pagingConfig) { storiesPagingSource.apply { query = it } }
                    .liveData
                    .cachedIn(viewModelScope)
                    .asFlow()
            }
    }

    fun setQuery(query: String): Job = viewModelScope.launch {
        _query.postValue(query)
    }
}
