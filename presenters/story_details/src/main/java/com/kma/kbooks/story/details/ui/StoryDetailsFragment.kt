package com.kma.kbooks.story.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kma.kbooks.domain.data.model.Genre
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.story.details.R
import com.kma.kbooks.story.details.injection.component.DaggerStoryDetailsComponent
import com.kma.kbooks.story.details.ui.component.*
import com.kma.kbooks.story.details.util.toString
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
            Box(modifier = Modifier.fillMaxSize()) {
                BackButton(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(vertical = 16.dp)
                        .padding(start = 16.dp),
                    onClick = this@StoryDetailsFragment::onBackPressed
                )

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(size = 48.dp)
                        .align(alignment = Alignment.Center),
                    strokeWidth = 4.dp
                )
            }
            return
        }

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .verticalScroll(state = scrollState)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Backdrop(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 256.dp),
                    data = storyDetails?.thumbnail
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 256.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.8f)
                                )
                            )
                        )
                ) {}

                BackButton(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(vertical = 16.dp)
                        .padding(start = 16.dp),
                    onClick = this@StoryDetailsFragment::onBackPressed,
                    tint = Color.White
                )

                Thumbnail(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 80.dp)
                        .width(width = 172.dp)
                        .align(alignment = Alignment.BottomCenter),
                    data = storyDetails?.thumbnail
                )
            }

            Text(   // Title
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = storyDetails?.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4
            )

            Text(   // Author
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = storyDetails?.author?.let { "($it)" }.orEmpty(),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )

            GenresSection(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                genres = storyDetails?.genres ?: emptyList()
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
                onClick = {}
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

    @Composable
    private fun GenresSection(
        modifier: Modifier = Modifier,
        genres: List<Genre>
    ) {
        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            items(items = genres) {
                GenreChip(
                    genre = it,
                    onClick = {}
                )
            }
        }
    }

    @Composable
    private fun MetadataSection(
        modifier: Modifier = Modifier,
        storyDetails: StoryDetails?
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MetadataColumn(
                key = stringResource(R.string.publish_date),
                value = storyDetails?.publishedAt?.toString("yyyy-MM-dd").orEmpty()
            )

            MetadataColumn(
                key = stringResource(R.string.rating),
                value = "${storyDetails?.rating ?: 0}/10"
            )

            MetadataColumn(
                key = stringResource(R.string.view),
                value = "${storyDetails?.view ?: 0}"
            )

            MetadataColumn(
                key = stringResource(R.string.status),
                value = "Đã hoàn thành"
            )
        }
    }
}