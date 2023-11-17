package com.example.faunaonboarding.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.faunaonboarding.R
import com.example.faunaonboarding.animal.AnimalDestination
import com.example.faunaonboarding.animalCreate.AnimalCreateDestination
import com.example.faunaonboarding.animalCreate.AnimalTypeSelectDestination
import com.example.faunaonboarding.compose.navigation.FaunaNavigationDestination
import com.example.faunaonboarding.compose.navigation.TopLevelDestination
import com.example.faunaonboarding.compose.Icon.DrawableResourceIcon
import com.example.faunaonboarding.login.AccessCodeDestination
import com.example.faunaonboarding.login.PhoneNumberDestination
import com.example.faunaonboarding.onboarding.OnBoardingDestination
import com.example.faunaonboarding.onboarding.userCreate.UserCreateDestination
import com.example.faunaonboarding.welcome.WelcomeDestination
import com.example.faunaonboarding.welcomeToFauna.WelcomeToFaunaDestination

@Composable
fun rememberFaunaAppState(
    navController: NavHostController = rememberNavController()
): FaunaAppState {
    return remember(navController) {
        FaunaAppState(navController)
    }
}

@Stable
class FaunaAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun shouldShowBottomBar(currentDestination: NavDestination?): Boolean {
        //return topLevelDestinations.find { it.route == currentDestination?.route } != null
        return currentDestination?.route != OnBoardingDestination.route
                && currentDestination?.route != WelcomeDestination.route
                && currentDestination?.route != AnimalCreateDestination.route
                && currentDestination?.route != PhoneNumberDestination.route
                && currentDestination?.route != UserCreateDestination.route
                && currentDestination?.route != AccessCodeDestination.route
                && currentDestination?.route != AnimalTypeSelectDestination.route
                && currentDestination?.route != WelcomeToFaunaDestination.route
                && currentDestination?.route != AnimalDestination.route
    }

    /**
     * Top level destinations to be used in the BottomBar
     */
    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = OnBoardingDestination.route,
            destination = OnBoardingDestination.destination,
            selectedIcon = DrawableResourceIcon(FaunaIcons.homeSelected),
            unselectedIcon = DrawableResourceIcon(FaunaIcons.homeUnselected),
            iconTextId = R.string.title_nav_bar_pet
        ),
    )

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * Top level destinations have only one copy of the destination of the back stack, and save and
     * restore state whenever you navigate to and from it.
     * Regular destinations can have multiple copies in the back stack and state isn't saved nor
     * restored.
     *
     * @param destination: The [FaunaNavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
    fun navigate(destination: FaunaNavigationDestination, route: String? = null) {
//        if (destination is TopLevelDestination) {
        navController.navigate(route ?: destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
//        } else {
//            navController.navigate(route ?: destination.route)
//        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}