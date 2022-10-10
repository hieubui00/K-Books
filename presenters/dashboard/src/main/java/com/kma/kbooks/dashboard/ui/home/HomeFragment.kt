package com.kma.kbooks.dashboard.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.dashboard.ui.DashboardFragment
import com.kma.kbooks.dashboard.ui.home.component.ActionBar
import com.kma.kbooks.dashboard.ui.home.component.MoreButton
import com.kma.kbooks.dashboard.ui.home.component.StoryCard
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<HomeViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val navHostFragment = parentFragment as? NavHostFragment
        val dashboardFragment = navHostFragment?.parentFragment as? DashboardFragment

        dashboardFragment?.component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            KBooksTheme {
                this@HomeFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            topBar = {
                ActionBar(title = stringResource(id = R.string.title_home))
            },
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = MaterialTheme.colors.surface)
                    .verticalScroll(scrollState)
            ) {
                val hotStories by viewModel.hotStories.observeAsState()
                val newUpdatedStories by viewModel.newUpdatedStories.observeAsState()
                val completedStories by viewModel.completedStories.observeAsState()

                hotStories?.let { stories ->
                    StoriesSection(
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                        label = stringResource(R.string.label_hot),
                        stories = stories
                    )
                }

                newUpdatedStories?.let { stories ->
                    StoriesSection(
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                        label = stringResource(R.string.label_new_updated),
                        stories = stories
                    )
                }

                completedStories?.let { stories ->
                    StoriesSection(
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                        label = stringResource(R.string.label_completed),
                        stories = stories
                    )
                }
            }
        }
    }

    @Composable
    private fun StoriesSection(
        modifier: Modifier = Modifier,
        label: String? = null,
        stories: List<Story> = listOf()
    ) {
        Column(modifier = modifier) {
            Text(   // Label
                modifier = Modifier.padding(start = 16.dp),
                text = label.orEmpty(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )

            if (stories.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
                        .align(alignment = CenterHorizontally),
                    text = "No stories found!",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption
                )
                return@Column
            }

            LazyRow(
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(items = stories) { story ->
                    StoryCard(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { }
                            )
                            .width(width = 128.dp),
                        story = story
                    )
                }

                item {
                    MoreButton(onClick = { })
                }
            }
        }
    }
}