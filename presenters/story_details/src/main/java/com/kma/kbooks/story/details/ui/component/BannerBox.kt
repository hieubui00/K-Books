package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BannerBox(
    modifier: Modifier = Modifier,
    backdrop: String?,
    poster: String?,
    onBackPressed: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Backdrop(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 256.dp),
            data = backdrop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 256.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
        ) {}

        BackButton(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 16.dp),
            onClick = onBackPressed,
            tint = Color.White
        )

        Poster(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 80.dp)
                .width(width = 172.dp)
                .align(alignment = Alignment.BottomCenter),
            data = poster
        )
    }
}
