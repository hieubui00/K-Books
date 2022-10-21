package com.kma.kbooks.chapter.details.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onSettingsClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        contentPadding = PaddingValues(horizontal = 16.dp),
        elevation = 8.dp,
    ) {
        ActionButton(
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            imageVector = Icons.Default.ArrowBack,
            onClick = onBackPress
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        ActionButton(
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            imageVector = Icons.Default.Settings,
            onClick = onSettingsClick
        )
    }
}
