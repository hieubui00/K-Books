package com.kma.kbooks.chapters.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kma.kbooks.chapters.data.source.ChaptersPagingSource
import com.kma.kbooks.chapters.injection.scope.ChaptersScope
import com.kma.kbooks.domain.data.model.Chapter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ChaptersScope
class ChaptersViewModel @Inject constructor(
    state: SavedStateHandle,

    private val chaptersPagingSource: ChaptersPagingSource
) : ViewModel() {
    val chapters: Flow<PagingData<Chapter>>

    init {
        val storyId = state.get<Int>("storyId") ?: -1
        val pagingConfig = PagingConfig(pageSize = 16)

        chaptersPagingSource.storyId = storyId
        chapters = Pager(pagingConfig) { chaptersPagingSource }
            .liveData
            .cachedIn(viewModelScope)
            .asFlow()
    }
}