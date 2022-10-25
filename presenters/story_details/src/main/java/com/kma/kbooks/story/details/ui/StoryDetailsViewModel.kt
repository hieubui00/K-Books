package com.kma.kbooks.story.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.story.details.injection.scope.StoryDetailsScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@StoryDetailsScope
class StoryDetailsViewModel @Inject constructor(
    state: SavedStateHandle,

    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _storyDetails = MutableLiveData<StoryDetails?>()

    val storyDetails: LiveData<StoryDetails?>
        get() = _storyDetails

    val storyId: Int

    init {
        storyId = state.get<Int>("storyId") ?: -1

        getStoryDetails(storyId = storyId)
    }

    private fun getStoryDetails(storyId: Int): Job = viewModelScope.launch {
        val storyDetails = storyRepository.getStoryDetails(storyId)

        _storyDetails.postValue(storyDetails)
    }

    fun setFavourite(isFavourite: Boolean): Job = viewModelScope.launch {
        _storyDetails.value?.copy(isFavourite = isFavourite)?.let {
            _storyDetails.postValue(it)
            if (isFavourite) storyRepository.saveStory(it) else storyRepository.deleteStory(it)
        }
    }
}
