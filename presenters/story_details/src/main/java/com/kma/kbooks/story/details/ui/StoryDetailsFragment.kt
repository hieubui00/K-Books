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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.story.details.R
import com.kma.kbooks.story.details.injection.component.DaggerStoryDetailsComponent
import com.kma.kbooks.story.details.ui.component.*
import com.kma.kbooks.ui.main.MainActivity

class StoryDetailsFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component

        DaggerStoryDetailsComponent.builder()
            .mainComponent(mainComponent)
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
        val scrollState = rememberScrollState()
        val story = Story(
            storyId = "1",
            title = "Tôi thấy hoa vàng trên cỏ xanh",
            author = "Nguyễn Nhật Ánh",
            thumbnail = "https://static.8cache.com/cover/o/eJzLyTDW1zVO8s1OMwjyyksu1w_LKDD1TvPNNqry1HeEAqeCZP2K0Arzwkhvy-CCfP1iA13PZBMjAD6rEqM=/toi-thay-hoa-vang-tren-co-xanh.jpg"
        )
        val genres = listOf("Tiên hiệp", "Kiếm hiệp")
        val summary =
            "Phim là một câu chuyện đầy cảm xúc về quê hương, về gia đình, về thời niên thiếu của mỗi người. Cậu bé Tường ngây thơ, đầy tình thương trong khi Thiều là người anh trai ích kỷ, hẹp hòi đến tàn nhẫn. Bên cạnh tình cảm anh em với những yêu thương, ghen ghét, đố kỵ, hối tiếc, ăn năn… còn là tình cảm bạn bè, kỷ niệm thời thơ ấu của lũ trẻ nhà quê nghèo ở miền Trung cuối những năm 1980. Ở đó có những cuộc cãi vã, đánh nhau; những trò chơi trẻ con thú vị; những giấc mơ cổ tích công chúa, hoàng tử; những hờn giận vu vơ, rung động đầu đời…"

        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .verticalScroll(state = scrollState)
        ) {
            Box {
                Backdrop(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 256.dp),
                    data = story.thumbnail
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

                IconButton(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(vertical = 16.dp)
                        .padding(start = 16.dp)
                        .size(size = 24.dp),
                    onClick = { activity?.onBackPressedDispatcher?.onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = null,
                    )
                }

                Thumbnail(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 80.dp)
                        .width(width = 160.dp)
                        .align(alignment = Alignment.BottomCenter),
                    data = story.thumbnail
                )
            }

            Text(   // Title
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = story.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4
            )

            Text(   // Author
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = story.author?.let { "($it)" }.orEmpty(),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )

            GenresSection(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                genres = genres
            )

            MetadataSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .height(height = 40.dp),
                shape = RoundedCornerShape(size = 16.dp),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(size = 18.dp),
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(width = 8.dp))

                Text(
                    color = MaterialTheme.colors.onPrimary,
                    text = stringResource(R.string.read_story),
                    style = MaterialTheme.typography.button
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.summary),
                style = MaterialTheme.typography.h6
            )

            Text( // Summary
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify,
                text = summary,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(height = 16.dp))
        }
    }

    @Composable
    private fun GenresSection(
        modifier: Modifier = Modifier,
        genres: List<String>
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
    private fun MetadataSection(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MetadataColumn(
                key = stringResource(R.string.publish_date),
                value = "2022-10-13"
            )

            MetadataColumn(
                key = stringResource(R.string.rating),
                value = "8.0/10"
            )

            MetadataColumn(
                key = stringResource(R.string.view),
                value = "29645"
            )

            MetadataColumn(
                key = stringResource(R.string.status),
                value = "Đang ra"
            )
        }
    }
}