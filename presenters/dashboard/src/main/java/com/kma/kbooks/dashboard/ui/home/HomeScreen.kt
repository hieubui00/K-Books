package com.kma.kbooks.dashboard.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kma.kbooks.dashboard.R.string
import com.kma.kbooks.dashboard.ui.home.component.ActionBar
import com.kma.kbooks.dashboard.ui.home.component.StorySection
import com.kma.kbooks.domain.data.model.Status
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.domain.util.SortBy
import com.kma.kbooks.domain.util.SortOrder

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateToStories: (Status?, Pair<SortBy, SortOrder>?) -> Unit,
    onNavigateToStoryDetails: (Story) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ActionBar(
                title = stringResource(id = string.title_home),
                onNavigationClick = {},
                onSearchClick = {}
            )
        },
    ) { padding ->
        val trendingStories by viewModel.trendingStories.observeAsState()
        val recommendedStories by viewModel.recommendedStories.observeAsState()
        val completedStories by viewModel.completedStories.observeAsState()

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(color = MaterialTheme.colors.surface),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            item {
                StorySection(
                    label = stringResource(id = string.label_trending_now),
                    stories = trendingStories,
                    onSeeMoreClick = { onNavigateToStories(null, SortBy.VIEW to SortOrder.DESC) },
                    onItemClick = onNavigateToStoryDetails
                )
            }

            item {
                StorySection(
                    label = stringResource(id = string.label_recommend),
                    stories = recommendedStories,
                    onSeeMoreClick = { onNavigateToStories(null, SortBy.RATING to SortOrder.DESC) },
                    onItemClick = onNavigateToStoryDetails
                )
            }

            item {
                StorySection(
                    label = stringResource(id = string.label_completed),
                    stories = completedStories,
                    onSeeMoreClick = { onNavigateToStories(Status.COMPLETED, null) },
                    onItemClick = onNavigateToStoryDetails
                )
            }
        }
    }
}
