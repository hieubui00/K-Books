package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.story.details.R

@Composable
internal fun ReadStoryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(size = 24.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(size = 18.dp),
            imageVector = Icons.Default.AutoStories,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(width = 8.dp))

        Text(
            color = MaterialTheme.colors.onPrimary,
            text = stringResource(R.string.read_story),
            style = MaterialTheme.typography.button
        )
    }
}

@Preview
@Composable
private fun ReadStoryButtonPreview() {
    KBooksTheme {
        ReadStoryButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 40.dp),
            onClick = {}
        )
    }
}
