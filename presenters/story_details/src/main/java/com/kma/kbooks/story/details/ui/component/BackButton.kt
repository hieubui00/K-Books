package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    tint: Color = MaterialTheme.colors.onBackground
) {
    IconButton(
        modifier = Modifier
            .statusBarsPadding()
            .then(modifier)
            .size(size = 24.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = tint,
            contentDescription = null,
        )
    }
}
