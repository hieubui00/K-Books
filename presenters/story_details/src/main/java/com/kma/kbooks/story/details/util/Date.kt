package com.kma.kbooks.story.details.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun Date.toString(pattern: String, locale: Locale = Locale.getDefault()): String {
    return SimpleDateFormat(pattern, locale).format(this)
}
