package com.kma.kbooks.search.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.component.StoryCard
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.search.injection.component.DaggerSearchComponent
import com.kma.kbooks.search.ui.component.ActionBar
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<SearchViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component
        DaggerSearchComponent.builder()
            .mainComponent(mainComponent)
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
                this@SearchFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            topBar = {
                ActionBar(
                    onNavigationClick = { activity?.onBackPressedDispatcher?.onBackPressed() },
                    onTextChange = {
                        viewModel.setQuery(it)
                    }
                )
            },
        ) {
            val stories = viewModel.stories.collectAsLazyPagingItems()

            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
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
            }
        }
    }

    private fun navigateToStoryDetails(story: Story) {
        val action = SearchFragmentDirections.navigateToStoryDetails(story.storyId)
        findNavController().navigate(action)
    }
}
