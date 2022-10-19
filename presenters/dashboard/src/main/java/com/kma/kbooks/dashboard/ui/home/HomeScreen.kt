package com.kma.kbooks.dashboard.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kma.kbooks.dashboard.R.string
import com.kma.kbooks.dashboard.ui.home.component.StorySection
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.component.ActionBar

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateToStories: () -> Unit,
    onNavigateToStoryDetails: (Story) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { ActionBar(title = stringResource(id = string.title_home)) },
    ) { padding ->
        val sectionContents = listOf(
            stringResource(id = string.label_trending_now) to viewModel.trendingStories.observeAsState(),
            stringResource(id = string.label_recommend) to viewModel.recommendedStories.observeAsState(),
            stringResource(id = string.label_completed) to viewModel.completedStories.observeAsState(),
        )

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(color = MaterialTheme.colors.surface),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            items(sectionContents) {
                StorySection(
                    label = it.first,
                    stories = it.second.value ?: emptyList(),
                    onSeeMoreClick = onNavigateToStories,
                    onItemClick = onNavigateToStoryDetails
                )
            }
        }
    }
}
