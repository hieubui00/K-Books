package com.kma.kbooks.chapters.injection.component

import androidx.lifecycle.SavedStateHandle
import com.kma.kbooks.chapters.injection.scope.ChaptersScope
import com.kma.kbooks.chapters.ui.ChaptersFragment
import com.kma.kbooks.injection.component.MainComponent
import dagger.BindsInstance
import dagger.Component

@ChaptersScope
@Component(dependencies = [MainComponent::class])
interface ChaptersComponent {

    fun inject(chaptersFragment: ChaptersFragment)

    @Component.Builder
    interface Builder {

        fun mainComponent(mainComponent: MainComponent?): Builder

        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder

        fun build(): ChaptersComponent
    }
}
