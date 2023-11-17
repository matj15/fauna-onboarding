package com.example.faunaonboarding.animalCreate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object AnimalCreateDestination : FaunaNavigationDestination {
    override val route = "animal_create_route"
    override val destination = "animal_create_destination"
}

fun NavGraphBuilder.animalCreateGraph(
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    composable(route = AnimalCreateDestination.route) {
        AnimalCreateRoute(
            onAnimalCreated = onContinueClick,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )
    }
}

