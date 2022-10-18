package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
internal fun Metadata(
    modifier: Modifier = Modifier,
    metadata: Pair<String?, String?>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 2.dp)
    ) {
        Text(
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            text = metadata.first.orEmpty(),
            style = MaterialTheme.typography.caption
        )

        Text(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = metadata.second.orEmpty(),
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview
@Composable
private fun MetadataColumnPreview() {
    KBooksTheme {
        Metadata(metadata = "Rating" to "8.0/10")
    }
}
