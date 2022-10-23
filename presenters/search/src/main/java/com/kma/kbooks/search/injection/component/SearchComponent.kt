package com.kma.kbooks.search.injection.component

import androidx.lifecycle.SavedStateHandle
import com.kma.kbooks.injection.component.MainComponent
import com.kma.kbooks.search.injection.scope.SearchScope
import com.kma.kbooks.search.ui.SearchFragment
import dagger.BindsInstance
import dagger.Component

@SearchScope
@Component(dependencies = [MainComponent::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

//    @Component.Builder
//    interface Builder {
//
//        fun mainComponent(mainComponent: MainComponent?): Builder
//
//        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder
//
//        fun build(): SearchFragment
//    }
}