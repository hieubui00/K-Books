package com.kma.kbooks.chapter.details.injection.component

import androidx.lifecycle.SavedStateHandle
import com.kma.kbooks.chapter.details.injection.scope.ChapterDetailsScope
import com.kma.kbooks.chapter.details.ui.ChapterDetailsFragment
import com.kma.kbooks.injection.component.MainComponent
import dagger.BindsInstance
import dagger.Component

@ChapterDetailsScope
@Component(dependencies = [MainComponent::class])
interface ChapterDetailsComponent {

    fun inject(chapterDetailsFragment: ChapterDetailsFragment)

    @Component.Builder
    interface Builder {

        fun mainComponent(mainComponent: MainComponent?): Builder

        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder

        fun build(): ChapterDetailsComponent
    }
}
