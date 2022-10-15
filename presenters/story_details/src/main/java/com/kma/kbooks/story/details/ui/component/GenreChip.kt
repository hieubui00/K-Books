package com.kma.kbooks.story.details.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun GenreChip(
    modifier: Modifier = Modifier,
    genre: String?,
    onClick: () -> Unit
) {
    Chip(
        modifier = modifier,
        colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = onClick
    ) {
        Text(
            color = MaterialTheme.colors.onPrimary,
            text = genre.orEmpty()
        )
    }
}

@Preview
@Composable
private fun GenreChipPreview() {
    KBooksTheme {
        GenreChip(
            genre = "Tiên hiệp",
            onClick = {}
        )
    }
}