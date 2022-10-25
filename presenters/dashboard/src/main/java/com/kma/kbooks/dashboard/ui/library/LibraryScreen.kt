package com.kma.kbooks.dashboard.ui.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.dashboard.ui.library.component.LoadingContent
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.component.StoryCard

@Composable
internal fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel,
    onNavigateToStoryDetails: (Story) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = modifier,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = "Menu"
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.title_library),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h5
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 8.dp
            )
        },
    ) {
        val stories = viewModel.stories.collectAsLazyPagingItems()
        val refreshState = rememberSwipeRefreshState(stories.loadState.refresh is LoadState.Loading)

        SwipeRefresh(
            modifier = Modifier.padding(it),
            state = refreshState,
            onRefresh = { stories.refresh() }
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(count = stories.itemCount) { index ->
                    stories[index]?.let { story ->
                        StoryCard(
                            title = story.title,
                            author = story.author,
                            thumbnail = story.thumbnail,
                            onClick = { onNavigateToStoryDetails(story) }
                        )
                    }
                }

                if (stories.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(currentLineSpan = Int.MAX_VALUE) }) {
                        LoadingContent(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}
