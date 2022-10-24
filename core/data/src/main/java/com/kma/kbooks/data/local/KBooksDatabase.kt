package com.kma.kbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kma.kbooks.data.local.dao.ChapterDao
import com.kma.kbooks.data.local.model.ChapterLocalModel

@Database(
    entities = [ChapterLocalModel::class],
    version = 1,
    exportSchema = false
)
abstract class KBooksDatabase : RoomDatabase() {

    abstract fun chapterDao(): ChapterDao
}