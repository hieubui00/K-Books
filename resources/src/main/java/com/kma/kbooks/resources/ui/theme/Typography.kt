package com.kma.kbooks.resources.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kma.kbooks.resources.R

internal val typography: Typography
    @Composable
    get() = Typography(
        defaultFontFamily = fontFamily,
        h1 = textStyle.copy(fontSize = 48.sp),
        h2 = textStyle.copy(fontSize = 32.sp),
        h3 = textStyle.copy(fontSize = 28.sp),
        h4 = textStyle.copy(fontSize = 22.sp),
        h5 = textStyle.copy(fontSize = 20.sp),
        h6 = textStyle.copy(fontSize = 18.sp),
        body1 = textStyle.copy(fontSize = 16.sp),
        button = textStyle.copy(fontSize = 16.sp),
        caption = textStyle.copy(fontSize = 14.sp),
    )

private val fontFamily = FontFamily(
    Font(
        resId = R.font.lato_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.lato_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.lato_bold,
        weight = FontWeight.Bold
    )
)

private val textStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = colorResource(id = R.color.onBackground),
    )
