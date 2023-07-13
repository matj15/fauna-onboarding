package com.example.faunaonboarding.animalCreate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination

object AnimalTypeSelectDestination : FaunaNavigationDestination {
    override val route = "animal_type_select_route"
    override val destination = "animal_type_select_destination"
}

fun NavGraphBuilder.animalTypeSelectGraph(
    showAnimalInfoCreate: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    composable(route = AnimalTypeSelectDestination.route) {
        AnimalTypeSelectRoute(
            onAnimalTypeSelected = showAnimalInfoCreate,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )
    }
}

