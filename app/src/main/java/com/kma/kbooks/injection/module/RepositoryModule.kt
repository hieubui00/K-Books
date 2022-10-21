package com.kma.kbooks.injection.module

import com.kma.kbooks.data.repository.ChapterRepositoryImpl
import com.kma.kbooks.data.repository.ReaderRepositoryImpl
import com.kma.kbooks.data.repository.StoryRepositoryImpl
import com.kma.kbooks.domain.data.repository.ChapterRepository
import com.kma.kbooks.domain.data.repository.ReaderRepository
import com.kma.kbooks.domain.data.repository.StoryRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        SharedPreferencesModule::class,
        DataSourceModule::class
    ]
)
abstract class RepositoryModule {

    @Binds
    abstract fun bindReaderRepository(readerRepositoryImpl: ReaderRepositoryImpl): ReaderRepository

    @Binds
    abstract fun bindStoryRepository(storyRepositoryImpl: StoryRepositoryImpl): StoryRepository

    @Binds
    abstract fun bindChapterRepository(chapterRepositoryImpl: ChapterRepositoryImpl): ChapterRepository
}
