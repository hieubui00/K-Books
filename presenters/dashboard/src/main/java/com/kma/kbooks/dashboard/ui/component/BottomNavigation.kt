package com.kma.kbooks.dashboard.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kma.kbooks.dashboard.util.Destination

@Composable
internal fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vararg destinations: Destination,
    onItemClick: (Destination) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 8.dp
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val route = backStackEntry?.destination?.route

        destinations.forEach {
            NavigationItem(
                icon = it.icon,
                label = stringResource(id = it.title),
                isSelected = route == it.route,
                onClick = { onItemClick(it) }
            )
        }
    }
}
