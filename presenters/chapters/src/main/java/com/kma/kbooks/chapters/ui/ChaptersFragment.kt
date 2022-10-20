package com.kma.kbooks.chapters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.kma.kbooks.chapters.R
import com.kma.kbooks.chapters.ui.component.ChapterCard
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.resources.ui.component.ActionBar
import com.kma.kbooks.resources.ui.theme.KBooksTheme

class ChaptersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@ChaptersFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            topBar = {
                ActionBar(
                    title = stringResource(R.string.chapters),
                    onBackPress = { activity?.onBackPressedDispatcher?.onBackPressed() }
                )
            }
        ) {
            val chapters = emptyList<Chapter>()

            LazyColumn(
                modifier = Modifier.padding(it),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {
                items(items = chapters) { chapter ->
                    ChapterCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        chapter = chapter,
                        onClick = { }
                    )
                }
            }
        }
    }
}