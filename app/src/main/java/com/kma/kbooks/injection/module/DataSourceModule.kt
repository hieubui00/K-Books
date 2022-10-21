package com.kma.kbooks.injection.module

import com.kma.kbooks.data.source.ChapterRemoteDataSource
import com.kma.kbooks.data.source.ChapterRemoteDataSourceImpl
import com.kma.kbooks.data.source.StoryRemoteDataSource
import com.kma.kbooks.data.source.StoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DispatcherModule::class
    ]
)
abstract class DataSourceModule {

    @Binds
    abstract fun bindStoryRemoteDataSource(storyRemoteDataSourceImpl: StoryRemoteDataSourceImpl): StoryRemoteDataSource

    @Binds
    abstract fun bindChapterRemoteDataSource(chapterRemoteDataSourceImpl: ChapterRemoteDataSourceImpl): ChapterRemoteDataSource
}
