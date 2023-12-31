package com.example.faunaonboarding.login

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object AccessCodeDestination : FaunaNavigationDestination {
    const val phoneNumberArg = "contentId"
    override val route = "otp_code_route/{$phoneNumberArg}"
    override val destination = "otp_code_destination"

    fun createNavigationRoute(phoneNumberArg: String): String {
        val encodedPhoneNumber = Uri.encode(phoneNumberArg)
        return "otp_code_route/$encodedPhoneNumber"
    }
}

fun NavGraphBuilder.accessCodeGraph(
    showAuthenticated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    composable(
        route = AccessCodeDestination.route,
        arguments = listOf(
            navArgument(AccessCodeDestination.phoneNumberArg) { type = NavType.StringType }
        )
    ) {
        AccessCodeRoute(showAuthenticated, onBackClick, onCloseClick)
    }
}