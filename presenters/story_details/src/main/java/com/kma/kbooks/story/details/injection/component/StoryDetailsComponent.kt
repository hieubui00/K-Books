package com.kma.kbooks.story.details.injection.component

import androidx.lifecycle.SavedStateHandle
import com.kma.kbooks.injection.component.MainComponent
import com.kma.kbooks.story.details.injection.scope.StoryDetailsScope
import com.kma.kbooks.story.details.ui.StoryDetailsFragment
import dagger.BindsInstance
import dagger.Component

@StoryDetailsScope
@Component(dependencies = [MainComponent::class])
interface StoryDetailsComponent {

    fun inject(storyDetailsFragment: StoryDetailsFragment)

    @Component.Builder
    interface Builder {

        fun mainComponent(mainComponent: MainComponent?): Builder

        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder

        fun build(): StoryDetailsComponent
    }
}
