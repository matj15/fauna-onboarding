package com.example.faunaonboarding.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object OnBoardingDestination : FaunaNavigationDestination {
    override val route = "on_boarding_route"
    override val destination = "on_boarding_destination"
}

fun NavGraphBuilder.onBoardingGraph(
    navigateToNewUserInfoRegistration: () -> Unit
) {
    composable(route = OnBoardingDestination.route) {
        OnBoardingRoute(navigateToNewUserInfoRegistration)
    }
}
