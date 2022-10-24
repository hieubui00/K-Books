package com.kma.kbooks.util

import androidx.room.TypeConverter
import java.util.Date

class Converter {

    @TypeConverter
    fun Date.toTimestamp(): Long = time

    @TypeConverter
    fun Long.toDate(): Date = Date(this)
}