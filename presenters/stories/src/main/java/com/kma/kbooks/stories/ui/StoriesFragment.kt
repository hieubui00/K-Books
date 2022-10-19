package com.kma.kbooks.stories.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kma.kbooks.R
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.component.ActionBar
import com.kma.kbooks.resources.ui.component.StoryCard
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.stories.injection.component.DaggerStoriesComponent
import com.kma.kbooks.stories.ui.component.LoadingContent
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class StoriesFragment : Fragment() {
    private val viewModel by viewModels<StoriesViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<StoriesViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component
        val args by navArgs<StoriesFragmentArgs>()

        DaggerStoriesComponent.builder()
            .mainComponent(mainComponent)
            .savedStateHandle(args.toSavedStateHandle())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@StoriesFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            topBar = {
                ActionBar(
                    title = stringResource(id = R.string.title_stories),
                    onBackPress = { activity?.onBackPressedDispatcher?.onBackPressed() }
                )
            },
        ) {
            val stories = viewModel.stories.collectAsLazyPagingItems()

            if (stories.loadState.refresh is LoadState.Loading) {
                LoadingContent()
                return@Scaffold
            }

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
                            onClick = { navigateToStoryDetails(story) }
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

    private fun navigateToStoryDetails(story: Story) {
        val action = StoriesFragmentDirections.navigateToStoryDetails(story.storyId)

        findNavController().navigate(action)
    }
}
