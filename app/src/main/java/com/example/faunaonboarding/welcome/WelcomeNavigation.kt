package com.example.faunaonboarding.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object WelcomeDestination : FaunaNavigationDestination {
    override val route = "welcome_route"
    override val destination = "welcome_destination"
}

fun NavGraphBuilder.welcomeGraph(
    onNewUserClick: () -> Unit,
    onLoginClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    composable(route = WelcomeDestination.route) {
        WelcomeRoute(onNewUserClick = onNewUserClick, onLoginClick = onLoginClick)
    }
    nestedGraphs()
}


