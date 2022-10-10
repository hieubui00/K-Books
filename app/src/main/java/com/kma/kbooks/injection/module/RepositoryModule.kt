package com.kma.kbooks.injection.module

import com.kma.kbooks.data.repository.StoryRepositoryImpl
import com.kma.kbooks.domain.data.repository.StoryRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun bindStoryRepository(storyRepositoryImpl: StoryRepositoryImpl): StoryRepository
}
