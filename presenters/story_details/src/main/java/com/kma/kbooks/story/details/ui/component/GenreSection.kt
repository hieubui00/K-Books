package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kma.kbooks.domain.data.model.Genre

@Composable
internal fun GenreSection(
    modifier: Modifier = Modifier,
    genres: List<Genre> = listOf(),
    onItemClick: (Genre) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items(items = genres) {
            GenreChip(
                genre = it,
                onClick = onItemClick
            )
        }
    }
}
