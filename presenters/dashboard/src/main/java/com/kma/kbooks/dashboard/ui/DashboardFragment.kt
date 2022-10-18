package com.kma.kbooks.dashboard.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.kma.kbooks.dashboard.injection.component.DaggerDashboardComponent
import com.kma.kbooks.dashboard.ui.component.BottomNavigation
import com.kma.kbooks.dashboard.ui.home.HomeScreen
import com.kma.kbooks.dashboard.ui.home.HomeViewModel
import com.kma.kbooks.dashboard.util.Destination
import com.kma.kbooks.domain.data.model.Story
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import javax.inject.Inject

class DashboardFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory<HomeViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component

        DaggerDashboardComponent.builder()
            .mainComponent(mainComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@DashboardFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        val navController = rememberNavController()
        val destinations = Destination.values()

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navController = navController,
                    destinations = destinations,
                    onItemClick = { destination ->
                        navController.navigate(destination.route) {
                            val startRoute = navController.graph.startDestinationRoute

                            startRoute?.let { popUpTo(it) { saveState = true } }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = Destination.HOME.route
            ) {
                composable(Destination.HOME.route) {
                    HomeScreen(
                        viewModel = viewModel(factory = factory),
                        onNavigateToStoryDetails = this@DashboardFragment::navigateToStoryDetails
                    )
                }
            }
        }
    }

    private fun navigateToStoryDetails(story: Story) {
        val action = DashboardFragmentDirections.navigateToStoryDetails(story.storyId)

        findNavController().navigate(action)
    }
}
