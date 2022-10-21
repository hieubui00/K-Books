package com.kma.kbooks.data.manager

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReaderManager @Inject constructor(
    private val settings: SharedPreferences
) {
    var fontSize: Float = 0f
        get() = settings.getFloat(KEY_FONT_SIZE, 16f)
        set(value) {
            field = value
            settings.edit { putFloat(KEY_FONT_SIZE, value) }
        }

    companion object {
        private const val KEY_FONT_SIZE = "font_size"
    }
}
