package com.kma.kbooks.chapter.details.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
internal fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(alignment = Alignment.Center),
            color = MaterialTheme.colors.primary,
            strokeWidth = 4.dp
        )
    }
}

@Preview
@Composable
private fun LoadingContentPreview() {
    KBooksTheme {
        LoadingContent()
    }
}
