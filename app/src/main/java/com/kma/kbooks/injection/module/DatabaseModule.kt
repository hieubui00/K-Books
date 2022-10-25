package com.kma.kbooks.injection.module

import android.app.Application
import androidx.room.Room
import com.kma.kbooks.data.local.KBooksDatabase
import com.kma.kbooks.data.local.dao.ChapterDao
import com.kma.kbooks.data.local.dao.StoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideKBooksDatabase(application: Application): KBooksDatabase {
        return Room.databaseBuilder(application, KBooksDatabase::class.java, "KBooks").build()
    }

    @Provides
    fun provideStoryDAO(kBooksDatabase: KBooksDatabase): StoryDao = kBooksDatabase.storyDao()

    @Provides
    fun provideChapterDAO(kBooksDatabase: KBooksDatabase): ChapterDao = kBooksDatabase.chapterDao()
}
