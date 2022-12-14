package com.kma.kbooks.resources.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Filled.Book: ImageVector by lazy {
    materialIcon(name = "Filled.Book") {
        materialPath {
            moveTo(18.0f, 2.0f)
            horizontalLineTo(6.0f)
            curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
            verticalLineToRelative(16.0f)
            curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
            horizontalLineToRelative(12.0f)
            curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
            verticalLineTo(4.0f)
            curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
            close()
            moveTo(6.0f, 4.0f)
            horizontalLineToRelative(5.0f)
            verticalLineToRelative(8.0f)
            lineToRelative(-2.5f, -1.5f)
            lineTo(6.0f, 12.0f)
            verticalLineTo(4.0f)
            close()
        }
    }
}
