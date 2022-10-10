package com.kma.kbooks.resources.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.kma.kbooks.resources.R

internal val colors: Colors
    @Composable
    get() = lightColors(
        primary = colorResource(id = R.color.primary),
        background = colorResource(id = R.color.background),
        surface = colorResource(id = R.color.surface),
        error = colorResource(id = R.color.error),
        onPrimary = Color.White,
        onBackground = colorResource(id = R.color.onBackground),
        onSurface = colorResource(id = R.color.onSurface),
        onError = Color.White
    )
