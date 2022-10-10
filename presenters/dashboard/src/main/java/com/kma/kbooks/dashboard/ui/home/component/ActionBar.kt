package com.kma.kbooks.dashboard.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
internal fun ActionBar(title: String? = null) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp),
        color = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        Box {
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
        ActionBar(title = stringResource(id = R.string.title_home))
    }
}
