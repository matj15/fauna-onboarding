package com.example.faunaonboarding.onboarding.userCreate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object UserCreateDestination : FaunaNavigationDestination {
    override val route = "user_create_route"
    override val destination = "user_create_destination"
}

fun NavGraphBuilder.userCreateGraph(
    showOtpVerificationCode: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    composable(route = UserCreateDestination.route) {
        UserCreateRoute(
            onContinueClick = showOtpVerificationCode,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )
    }
}