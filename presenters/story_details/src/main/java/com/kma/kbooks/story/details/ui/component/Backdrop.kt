package com.kma.kbooks.story.details.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

@Composable
internal fun Backdrop(
    modifier: Modifier = Modifier,
    data: String?,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val model = ImageRequest.Builder(context)
        .data(data)
        .dispatcher(Dispatchers.IO)
        .crossfade(durationMillis = 400)
        .build()

    AsyncImage(
        modifier = modifier,
        model = model,
        contentScale = contentScale,
        contentDescription = null,
    )
}