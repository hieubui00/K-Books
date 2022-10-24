package com.kma.kbooks.injection.module

import com.kma.kbooks.data.local.source.ChapterLocalDataSource
import com.kma.kbooks.data.local.source.ChapterLocalDataSourceImpl
import com.kma.kbooks.data.local.source.StoryLocalDataSource
import com.kma.kbooks.data.local.source.StoryLocalDataSourceImpl
import com.kma.kbooks.data.remote.source.ChapterRemoteDataSource
import com.kma.kbooks.data.remote.source.ChapterRemoteDataSourceImpl
import com.kma.kbooks.data.remote.source.StoryRemoteDataSource
import com.kma.kbooks.data.remote.source.StoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DatabaseModule::class,
        NetworkModule::class,
        DispatcherModule::class
    ]
)
abstract class DataSourceModule {

    @Binds
    abstract fun bindStoryLocalDataSource(storyLocalDataSourceImpl: StoryLocalDataSourceImpl): StoryLocalDataSource

    @Binds
    abstract fun bindStoryRemoteDataSource(storyRemoteDataSourceImpl: StoryRemoteDataSourceImpl): StoryRemoteDataSource

    @Binds
    abstract fun bindChapterLocalDataSource(chapterLocalDataSourceImpl: ChapterLocalDataSourceImpl): ChapterLocalDataSource

    @Binds
    abstract fun bindChapterRemoteDataSource(chapterRemoteDataSourceImpl: ChapterRemoteDataSourceImpl): ChapterRemoteDataSource
}
