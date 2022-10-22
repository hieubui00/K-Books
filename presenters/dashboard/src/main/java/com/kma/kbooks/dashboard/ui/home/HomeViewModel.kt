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
    private val _trendingStories = MutableLiveData<List<Story>>()

    private val _recommendedStories = MutableLiveData<List<Story>>()

    private val _completedStories = MutableLiveData<List<Story>>()

    val trendingStories: LiveData<List<Story>>
        get() = _trendingStories

    val recommendedStories: LiveData<List<Story>>
        get() = _recommendedStories

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
        val trendingStories = storyRepository.getStories(sort = SortBy.VIEW to SortOrder.DESC)

        _trendingStories.postValue(trendingStories)
    }

    private suspend fun getRecommendedStories() {
        val recommendedStories = storyRepository.getStories(sort = SortBy.RATING to SortOrder.DESC)

        _recommendedStories.postValue(recommendedStories)
    }

    private suspend fun getCompletedStories() {
        val status = arrayOf(Status.COMPLETED)
        val completedStories = storyRepository.getStories(status = status, sort = SortBy.VIEW to SortOrder.DESC)

        _completedStories.postValue(completedStories)
    }
}
