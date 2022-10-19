package com.kma.kbooks.stories.injection.component

import androidx.lifecycle.SavedStateHandle
import com.kma.kbooks.injection.component.MainComponent
import com.kma.kbooks.stories.injection.scope.StoriesScope
import com.kma.kbooks.stories.ui.StoriesFragment
import dagger.BindsInstance
import dagger.Component

@StoriesScope
@Component(dependencies = [MainComponent::class])
interface StoriesComponent {

    fun inject(storiesFragment: StoriesFragment)

    @Component.Builder
    interface Builder {

        fun mainComponent(mainComponent: MainComponent?): Builder

        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder

        fun build(): StoriesComponent
    }
}
