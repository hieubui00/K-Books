package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
internal fun Poster(
    modifier: Modifier = Modifier,
    data: String?
) {
    Card(
        modifier = modifier.aspectRatio(ratio = 2f / 3f),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = 8.dp
    ) {
        Backdrop(
            data = data,
            contentScale = ContentScale.FillBounds
        )
    }
}
