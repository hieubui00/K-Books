package com.kma.kbooks.stories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.kma.kbooks.R
import com.kma.kbooks.resources.ui.component.ActionBar
import com.kma.kbooks.resources.ui.component.StoryCard
import com.kma.kbooks.resources.ui.theme.KBooksTheme

class StoriesFragment : Fragment() {

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
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(count = 20) { index ->
                    StoryCard(
                        title = "Tôi thấy hoa vàng trên cỏ xanh",
                        author = "Nguyễn Nhật Ánh",
                        thumbnail = "https://upload.wikimedia.org/wikipedia/vi/thumb/f/f8/Toithayhoavangtrencoxanh.jpg/280px-Toithayhoavangtrencoxanh.jpg",
                        onClick = { }
                    )
                }
            }
        }
    }
}