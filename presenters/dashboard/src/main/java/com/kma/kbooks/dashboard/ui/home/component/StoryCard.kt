package com.kma.kbooks.dashboard.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import kotlinx.coroutines.Dispatchers

@Composable
internal fun StoryCard(
    modifier: Modifier = Modifier,
    story: Story,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val model = ImageRequest.Builder(context)
        .data(story.thumbnail)
        .dispatcher(Dispatchers.IO)
        .crossfade(durationMillis = 400)
        .build()

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        AsyncImage( // Poster
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 3f / 4f),
            model = model,
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(R.string.thumbnail)
        )

        Text( // Title
            text = story.title.orEmpty(),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.body1
        )

        Text( // Author
            text = story.author.orEmpty(),
            color = Color(0xFF888E96),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview
@Composable
private fun StoryCardPreview() {
    KBooksTheme {
        StoryCard(
            modifier = Modifier.background(color = Color.White),
            story = Story(
                storyId = 1,
                title = "Tôi thấy hoa vàng trên cỏ xanh",
                author = "Nguyễn Nhật Ánh",
                thumbnail = null
            ),
            onClick = {}
        )
    }
}
