package com.kbooks.dashboard.injection.component

import com.kbooks.dashboard.injection.scope.DashboardScope
import com.kbooks.dashboard.ui.home.HomeFragment
import com.kma.kbooks.injection.component.MainComponent
import dagger.Component

@DashboardScope
@Component(dependencies = [MainComponent::class])
interface DashboardComponent {

    fun inject(homeFragment: HomeFragment)
}