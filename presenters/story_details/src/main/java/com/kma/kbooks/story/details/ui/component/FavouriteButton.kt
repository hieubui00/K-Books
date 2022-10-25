package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun FavouriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    tint: Color = MaterialTheme.colors.onBackground
) {
    IconToggleButton(
        modifier = Modifier
            .statusBarsPadding()
            .then(modifier)
            .size(size = 24.dp),
        checked = isFavorite,
        onCheckedChange = onCheckedChange
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            tint = if (isFavorite) MaterialTheme.colors.error else tint,
            contentDescription = null,
        )
    }
}
