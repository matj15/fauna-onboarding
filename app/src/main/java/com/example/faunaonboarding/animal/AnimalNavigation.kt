package com.example.faunaonboarding.animal

import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object AnimalDestination : FaunaNavigationDestination {
    override val route = "animal_route"
    override val destination = "animal_destination"
}

fun NavGraphBuilder.animalGraph(
    showWelcome: () -> Unit
) {
    navigation(
        route = AnimalDestination.route,
        startDestination = AnimalDestination.destination
    ) {
        composable(route = AnimalDestination.destination) {
            val uriHandler = LocalUriHandler.current
            AnimalRoute(
                showPlayStore = {
                    uriHandler.openUri("https://play.google.com/store/apps/details?id=com.fauna.faunaapp")
                },
                showWelcome = showWelcome
            )
        }
    }
}

