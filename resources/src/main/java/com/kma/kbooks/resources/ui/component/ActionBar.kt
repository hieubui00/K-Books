package com.kma.kbooks.resources.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
fun ActionBar(
    title: String? = null,
    onBackPress: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp),
        color = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        Box {
            onBackPress?.let {
                IconButton(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(size = 24.dp)
                        .align(alignment = Alignment.CenterStart),
                    onClick = it
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Back",
                    )
                }
            }

            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = title.orEmpty(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
private fun ActionBarPreview() {
    KBooksTheme {
        ActionBar(
            title = "Action Bar",
            onBackPress = { }
        )
    }
}
