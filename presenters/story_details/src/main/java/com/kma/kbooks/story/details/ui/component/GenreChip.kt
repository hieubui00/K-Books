package com.kma.kbooks.story.details.ui.component

import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kma.kbooks.domain.data.model.Genre
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun GenreChip(
    modifier: Modifier = Modifier,
    genre: Genre,
    onClick: (Genre) -> Unit
) {
    Chip(
        modifier = modifier,
        colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = { onClick(genre) }
    ) {
        Text(
            color = MaterialTheme.colors.onPrimary,
            text = genre.name.orEmpty(),
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview
@Composable
private fun GenreChipPreview() {
    KBooksTheme {
        GenreChip(
            genre = Genre(
                genreId = 1,
                name = "Tiên hiệp"
            ),
            onClick = {}
        )
    }
}
