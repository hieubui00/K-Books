package com.kma.kbooks.injection.component

import android.content.Context
import com.kma.kbooks.domain.data.repository.StoryRepository
import com.kma.kbooks.injection.scope.MainScope
import com.kma.kbooks.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@MainScope
@Subcomponent
interface MainComponent {

    fun storyRepository(): StoryRepository

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): MainComponent
    }
}
