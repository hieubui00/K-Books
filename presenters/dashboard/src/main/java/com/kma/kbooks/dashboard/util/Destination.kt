package com.kma.kbooks.dashboard.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.resources.ui.component.Book

enum class Destination(
    @StringRes val title: Int,

    val icon: ImageVector,

    val route: String,
) {
    HOME(R.string.title_home, Icons.Default.Home, "home"),
    LIBRARY(R.string.title_library, Icons.Default.Book, "library")
}
