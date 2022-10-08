package com.kma.kbooks.resources.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kma.kbooks.resources.R

@Composable
fun KBooksTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}