package com.example.faunaonboarding.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object PhoneNumberDestination : FaunaNavigationDestination {
    override val route = "phone_number_route"
    override val destination = "phone_number_destination"
}

fun NavGraphBuilder.phoneNumberGraph(
    showLogin: (String) -> Unit
) {
    composable(route = PhoneNumberDestination.route) {
        PhoneNumberRoute(showLogin)
    }
}