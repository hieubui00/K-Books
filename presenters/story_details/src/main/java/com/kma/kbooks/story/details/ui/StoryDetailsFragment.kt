package com.kma.kbooks.story.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.story.details.R
import com.kma.kbooks.story.details.injection.component.DaggerStoryDetailsComponent
import com.kma.kbooks.story.details.ui.component.BannerBox
import com.kma.kbooks.story.details.ui.component.GenreSection
import com.kma.kbooks.story.details.ui.component.LoadingContent
import com.kma.kbooks.story.details.ui.component.MetadataSection
import com.kma.kbooks.story.details.ui.component.ReadStoryButton
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class StoryDetailsFragment : Fragment() {
    private val viewModel by viewModels<StoryDetailsViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<StoryDetailsViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component
        val args by navArgs<StoryDetailsFragmentArgs>()

        DaggerStoryDetailsComponent.builder()
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
        setContent {
            KBooksTheme {
                this@StoryDetailsFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        val storyDetails by viewModel.storyDetails.observeAsState()

        if (storyDetails == null) {
            LoadingContent(onBackPressed = this::onBackPressed)
            return
        }

        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .verticalScroll(state = rememberScrollState())
        ) {
            BannerBox(
                backdrop = storyDetails?.thumbnail,
                poster = storyDetails?.thumbnail,
                isFavourite = storyDetails?.isFavourite ?: false,
                onBackPressed = this@StoryDetailsFragment::onBackPressed,
                onFavouriteChange = { viewModel.setFavourite(it) }
            )

            Text( // Title
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = storyDetails?.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4
            )

            Text( // Author
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = storyDetails?.author?.let { "($it)" }.orEmpty(),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )

            GenreSection(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                genres = storyDetails?.genres ?: emptyList(),
                onItemClick = { }
            )

            MetadataSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                storyDetails = storyDetails
            )

            ReadStoryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .height(height = 48.dp),
                onClick = this@StoryDetailsFragment::navigateToChapters
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.summary),
                style = MaterialTheme.typography.h5
            )

            Text( // Summary
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify,
                text = storyDetails?.summary.orEmpty(),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(height = 16.dp))
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    private fun navigateToChapters() {
        val action = StoryDetailsFragmentDirections.navigateToChapters(viewModel.storyId)

        findNavController().navigate(action)
    }
}
