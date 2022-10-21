package com.kma.kbooks.chapters.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.R
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onNavigationClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                modifier = Modifier,
                text = title.orEmpty(),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    )
}

@Preview
@Composable
private fun ActionBarPreview() {
    KBooksTheme {
        ActionBar(
            title = stringResource(id = R.string.title_chapters),
            onNavigationClick = {},
        )
    }
}