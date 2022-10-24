package com.kma.kbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kma.kbooks.data.local.dao.ChapterDao
import com.kma.kbooks.data.local.dao.StoryDao
import com.kma.kbooks.data.local.model.ChapterLocalModel
import com.kma.kbooks.data.local.model.StoryLocalModel
import com.kma.kbooks.util.Converter

@Database(
    entities = [
        StoryLocalModel::class,
        ChapterLocalModel::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [Converter::class])
abstract class KBooksDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    abstract fun chapterDao(): ChapterDao
}