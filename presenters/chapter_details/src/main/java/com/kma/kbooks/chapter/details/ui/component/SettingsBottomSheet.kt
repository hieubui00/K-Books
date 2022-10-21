package com.kma.kbooks.chapter.details.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun SettingsBottomSheet(
    fontSize: MutableState<Float>,
    onClose: () -> Unit,
    onFontSizeChanged: (Float) -> Unit,
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp),
    ) {
        TopAppBar(
            modifier = Modifier.height(height = 48.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            elevation = 4.dp
        ) {
            ActionButton(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colors.onPrimary,
                onClick = onClose
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Settings",
                style = MaterialTheme.typography.body1
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            text = "Font size",
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.body1
        )

        Slider(
            modifier = Modifier.padding(horizontal = 16.dp),
            valueRange = 12f..24f,
            steps = 5,
            value = fontSize.value,
            onValueChange = { fontSize.value = it },
            onValueChangeFinished = { onFontSizeChanged(fontSize.value) }
        )
    }
}
