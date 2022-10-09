package com.kbooks.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbooks.dashboard.injection.scope.DashboardScope
import com.kbooks.domain.data.model.Story
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@DashboardScope
class HomeViewModel @Inject constructor() : ViewModel() {
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
        val stories = (0..20).map {
            Story(
                title = "Tôi thấy hoa vàng trên cỏ xanh",
                author = "Nguyễn Nhật Ánh",
                thumbnail = "https://static.8cache.com/cover/o/eJzLyTDW1zVO8s1OMwjyyksu1w_LKDD1TvPNNqry1HeEAqeCZP2K0Arzwkhvy-CCfP1iA13PZBMjAD6rEqM=/toi-thay-hoa-vang-tren-co-xanh.jpg"
            )
        }

        _hotStories.postValue(stories)
        _newUpdatedStories.postValue(stories)
        _completedStories.postValue(stories)
    }
}