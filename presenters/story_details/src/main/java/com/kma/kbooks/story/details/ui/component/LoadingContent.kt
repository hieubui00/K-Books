package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
internal fun LoadingContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        BackButton(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 16.dp),
            onClick = onBackPressed
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(size = 48.dp)
                .align(alignment = Alignment.Center),
            strokeWidth = 4.dp
        )
    }
}

@Preview
@Composable
private fun LoadingContentPreview() {
    KBooksTheme {
        LoadingContent(onBackPressed = {})
    }
}
