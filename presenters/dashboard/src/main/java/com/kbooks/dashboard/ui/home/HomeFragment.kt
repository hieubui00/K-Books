package com.kbooks.dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.kbooks.dashboard.R
import com.kbooks.dashboard.ui.home.component.ActionBar
import com.kbooks.dashboard.ui.home.component.StoryCard
import com.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.theme.KBooksTheme

class HomeFragment : Fragment() {

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
                val stories = (0..20).map {
                    Story(
                        title = "Tôi thấy hoa vàng trên cỏ xanh",
                        author = "Nguyễn Nhật Ánh",
                        thumbnail = "https://static.8cache.com/cover/o/eJzLyTDW1zVO8s1OMwjyyksu1w_LKDD1TvPNNqry1HeEAqeCZP2K0Arzwkhvy-CCfP1iA13PZBMjAD6rEqM=/toi-thay-hoa-vang-tren-co-xanh.jpg"
                    )
                }

                StoriesSection(
                    modifier = Modifier.padding(top = 16.dp),
                    label = stringResource(R.string.label_hot),
                    stories = stories
                )

                StoriesSection(
                    modifier = Modifier.padding(top = 24.dp),
                    label = stringResource(R.string.label_new_updated),
                    stories = stories
                )

                StoriesSection(
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                    label = stringResource(R.string.label_completed),
                    stories = stories
                )
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
            }
        }
    }
}