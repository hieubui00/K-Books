package com.kma.kbooks.chapters.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.compose.items
import com.kma.kbooks.chapters.R
import com.kma.kbooks.chapters.injection.component.DaggerChaptersComponent
import com.kma.kbooks.chapters.ui.component.ChapterCard
import com.kma.kbooks.chapters.ui.component.LoadingContent
import com.kma.kbooks.domain.data.model.Chapter
import com.kma.kbooks.resources.ui.component.ActionBar
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class ChaptersFragment : Fragment() {
    private val viewModel by viewModels<ChaptersViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<ChaptersViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component
        val args by navArgs<ChaptersFragmentArgs>()

        DaggerChaptersComponent.builder()
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
            val chapters = viewModel.chapters.collectAsLazyPagingItems()

            if (chapters.loadState.refresh is LoadState.Loading) {
                LoadingContent()
                return@Scaffold
            }

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
                        chapter = chapter!!,
                        onClick = this@ChaptersFragment::navigateToChapterDetails
                    )
                }

                if (chapters.loadState.append is LoadState.Loading) {
                    item {
                        LoadingContent(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }

    private fun navigateToChapterDetails(chapter: Chapter) {
        val action = ChaptersFragmentDirections.navigateToChapterDetails(chapter)

        findNavController().navigate(action)
    }
}
