package com.example.faunaonboarding.welcomeToFauna

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object WelcomeToFaunaDestination : FaunaNavigationDestination {
    override val route = "welcome_to_fauna_route"
    override val destination = "welcome_to_fauna_destination"
}

fun NavGraphBuilder.welcomeToFaunaGraph(
    onReadyClick: () -> Unit
) {
    composable(route = WelcomeToFaunaDestination.route) {
        WelcomeToFaunaRoute {
            onReadyClick()
        }
    }
}