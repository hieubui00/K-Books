package com.kma.kbooks.story.details.injection.component

import com.kma.kbooks.injection.component.MainComponent
import com.kma.kbooks.story.details.injection.scope.StoryDetailsScope
import com.kma.kbooks.story.details.ui.StoryDetailsFragment
import dagger.Component

@StoryDetailsScope
@Component(dependencies = [MainComponent::class])
interface StoryDetailsComponent {

    fun inject(storyDetailsFragment: StoryDetailsFragment)
}