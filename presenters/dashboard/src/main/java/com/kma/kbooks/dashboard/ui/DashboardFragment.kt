package com.kma.kbooks.dashboard.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.kma.kbooks.dashboard.R
import com.kma.kbooks.dashboard.injection.component.DaggerDashboardComponent
import com.kma.kbooks.dashboard.injection.component.DashboardComponent
import com.kma.kbooks.ui.main.MainActivity

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    lateinit var component: DashboardComponent
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component

        component = DaggerDashboardComponent.builder()
            .mainComponent(mainComponent)
            .build()
    }
}