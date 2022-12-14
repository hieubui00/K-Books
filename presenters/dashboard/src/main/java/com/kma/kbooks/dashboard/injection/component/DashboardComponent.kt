package com.kma.kbooks.dashboard.injection.component

import com.kma.kbooks.dashboard.injection.scope.DashboardScope
import com.kma.kbooks.dashboard.ui.DashboardFragment
import com.kma.kbooks.injection.component.MainComponent
import dagger.Component

@DashboardScope
@Component(dependencies = [MainComponent::class])
interface DashboardComponent {

    fun inject(dashboardFragment: DashboardFragment)
}
