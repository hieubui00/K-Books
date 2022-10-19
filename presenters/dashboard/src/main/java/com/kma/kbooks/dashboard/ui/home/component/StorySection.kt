package com.kma.kbooks.dashboard.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.component.StoryCard

@Composable
internal fun StorySection(
    modifier: Modifier = Modifier,
    label: String? = null,
    stories: List<Story> = listOf(),
    onSeeMoreClick: () -> Unit,
    onItemClick: (Story) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            val interactionSource = remember { MutableInteractionSource() }

            Text( // Label
                modifier = Modifier.padding(start = 16.dp),
                text = label.orEmpty(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )

            Text(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onSeeMoreClick
                    ),
                text = stringResource(R.string.see_more),
                style = MaterialTheme.typography.caption.copy(color = Color(0xFF888E96))
            )
        }

        if (stories.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 72.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = stringResource(R.string.message_no_stories_found),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
            return@Column
        }

        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            items(items = stories) {
                StoryCard(
                    modifier = Modifier.width(width = 160.dp),
                    title = it.title,
                    author = it.author,
                    thumbnail = it.thumbnail,
                    onClick = { onItemClick(it) }
                )
            }
        }
    }
}
