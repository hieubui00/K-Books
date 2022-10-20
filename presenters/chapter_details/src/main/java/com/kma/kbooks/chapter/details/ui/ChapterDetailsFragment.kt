package com.kma.kbooks.chapter.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.kma.kbooks.resources.ui.component.ActionBar
import com.kma.kbooks.resources.ui.theme.KBooksTheme

class ChapterDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@ChapterDetailsFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        val state = rememberLazyListState()
        val index = remember { derivedStateOf { state.firstVisibleItemIndex } }

        Scaffold(topBar = {
            if (index.value == 0) ActionBar(
                title = null,
                onBackPress = { activity?.onBackPressedDispatcher?.onBackPressed() }
            )
        }) {
            LazyColumn(
                modifier = Modifier.padding(it),
                state = state,
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = "Title",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4
                    )
                }

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = "Content",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}