package com.kma.kbooks.chapter.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.kbooks.chapter.details.injection.scope.ChapterDetailsScope
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.domain.data.model.ChapterDetails
import com.kma.kbooks.domain.data.repository.ChapterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ChapterDetailsScope
class ChapterDetailsViewModel @Inject constructor(
    state: SavedStateHandle,

    private val chapterRepository: ChapterRepository
) : ViewModel() {
    private val _chapterDetails = MutableLiveData<ChapterDetails?>()

    val chapterDetails: LiveData<ChapterDetails?>
        get() = _chapterDetails

    init {
        val chapter = state.get<Chapter>("chapter")

        chapter?.let { getChapterDetails(it.chapterId) }
    }

    private fun getChapterDetails(chapterId: Int): Job = viewModelScope.launch {
        val chapterDetails = chapterRepository.getChapterDetails(chapterId)

        _chapterDetails.postValue(chapterDetails)
    }
}
