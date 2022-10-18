package com.kma.kbooks.dashboard.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.domain.data.model.Story

@Composable
internal fun StorySection(
    modifier: Modifier = Modifier,
    label: String? = null,
    stories: List<Story> = listOf(),
    onItemClick: (Story) -> Unit
) {
    Column(modifier = modifier) {
        Text( // Label
            modifier = Modifier.padding(start = 16.dp),
            text = label.orEmpty(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )

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
                    story = it,
                    onClick = { onItemClick(it) }
                )
            }
        }
    }
}