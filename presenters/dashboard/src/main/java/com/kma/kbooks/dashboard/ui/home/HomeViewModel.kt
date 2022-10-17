package com.kma.kbooks.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.kbooks.dashboard.injection.scope.DashboardScope
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder
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
        launch { getTrendingStories() }
        launch { getRecommendedStories() }
        launch { getCompletedStories() }
    }

    private suspend fun getTrendingStories() {
        val hotStories = storyRepository.getStories(sort = SortBy.VIEW to SortOrder.DESC)

        _hotStories.postValue(hotStories)
    }

    private suspend fun getRecommendedStories() {
        val newUpdatedStories = storyRepository.getStories(sort = SortBy.RATING to SortOrder.DESC)

        _newUpdatedStories.postValue(newUpdatedStories)
    }

    private suspend fun getCompletedStories() {
        val completedStories = storyRepository.getStories(Status.COMPLETED)

        _completedStories.postValue(completedStories)
    }
}
