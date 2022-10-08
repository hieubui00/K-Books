package com.kma.kbooks.resources.ui.theme

import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kma.kbooks.resources.R

val typography: Typography
    @Composable
    get() = Typography(
        defaultFontFamily = fontFamily,
        h1 = TextStyle(
            color = colors.onBackground,
            fontSize = 48.sp,
            lineHeight = 12.sp,
        ),
        h2 = TextStyle(
            color = colors.onBackground,
            fontSize = 32.sp,
            lineHeight = 8.sp,
        ),
        h3 = TextStyle(
            color = colors.onBackground,
            fontSize = 28.sp,
            lineHeight = 7.sp,
        ),
        h4 = TextStyle(
            color = colors.onBackground,
            fontSize = 22.sp,
            lineHeight = 5.5.sp,
        ),
        h5 = TextStyle(
            color = colors.onBackground,
            fontSize = 20.sp,
            lineHeight = 5.sp,
        ),
        h6 = TextStyle(
            color = colors.onBackground,
            fontSize = 18.sp,
            lineHeight = 4.5.sp,
        ),
        body1 = TextStyle(
            color = colors.onBackground,
            fontSize = 16.sp,
            lineHeight = 4.sp,
        ),
        button = TextStyle(
            color = colors.onBackground,
            fontSize = 16.sp,
            lineHeight = 4.sp,
        ),
        caption = TextStyle(
            color = colors.onBackground,
            fontSize = 14.sp,
            lineHeight = 3.sp,
        ),
    )

val fontFamily = FontFamily(
    Font(R.font.lato_regular, weight = FontWeight.Normal),
    Font(R.font.lato_medium, weight = FontWeight.Medium),
    Font(R.font.lato_bold, weight = FontWeight.Bold)
)