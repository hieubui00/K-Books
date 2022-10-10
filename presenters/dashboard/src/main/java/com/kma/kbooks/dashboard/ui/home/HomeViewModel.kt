package com.kma.kbooks.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.kbooks.dashboard.injection.scope.DashboardScope
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@DashboardScope
class HomeViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _hotStories = MutableLiveData<List<Story>>()

    private val _newUpdatedStories = MutableLiveData<List<Story>>()

    private val _completedStories = MutableLiveData<List<Story>>()

    val hotStories: LiveData<List<Story>>
        get() = _hotStories

    val newUpdatedStories: LiveData<List<Story>>
        get() = _newUpdatedStories

    val completedStories: LiveData<List<Story>>
        get() = _completedStories

    init {
        getData()
    }

    private fun getData(): Job = viewModelScope.launch(context = SupervisorJob()) {
        launch { getHotStories() }
        launch { getNewUpdatedStories() }
        launch { getCompletedStories() }
    }

    private suspend fun getHotStories() {
        val hotStories = storyRepository.getHotStories()

        _hotStories.postValue(hotStories)
    }

    private suspend fun getNewUpdatedStories() {
        val newUpdatedStories = storyRepository.getNewUpdatedStories()

        _newUpdatedStories.postValue(newUpdatedStories)
    }

    private suspend fun getCompletedStories() {
        val completedStories = storyRepository.getCompletedStories()

        _completedStories.postValue(completedStories)
    }
}