package com.kma.kbooks.search.injection.component

import com.kma.kbooks.injection.component.MainComponent
import com.kma.kbooks.search.injection.scope.SearchScope
import com.kma.kbooks.search.ui.SearchFragment
import dagger.Component

@SearchScope
@Component(dependencies = [MainComponent::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)
}