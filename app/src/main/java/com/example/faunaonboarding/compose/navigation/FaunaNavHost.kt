package com.example.faunaonboarding.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.faunaonboarding.animal.AnimalDestination
import com.example.faunaonboarding.animal.animalGraph
import com.example.faunaonboarding.animalCreate.AnimalCreateDestination
import com.example.faunaonboarding.animalCreate.AnimalTypeSelectDestination
import com.example.faunaonboarding.animalCreate.animalCreateGraph
import com.example.faunaonboarding.animalCreate.animalTypeSelectGraph
import com.example.faunaonboarding.login.AccessCodeDestination
import com.example.faunaonboarding.login.PhoneNumberDestination
import com.example.faunaonboarding.login.accessCodeGraph
import com.example.faunaonboarding.login.phoneNumberGraph
import com.example.faunaonboarding.onboarding.OnBoardingDestination
import com.example.faunaonboarding.onboarding.onBoardingGraph
import com.example.faunaonboarding.onboarding.userCreate.UserCreateDestination
import com.example.faunaonboarding.onboarding.userCreate.userCreateGraph
import com.example.faunaonboarding.welcome.WelcomeDestination
import com.example.faunaonboarding.welcome.welcomeGraph
import com.example.faunaonboarding.welcomeToFauna.WelcomeToFaunaDestination
import com.example.faunaonboarding.welcomeToFauna.welcomeToFaunaGraph

@Composable
fun FaunaNavHost(
    navController: NavHostController,
    onNavigateToDestination: (FaunaNavigationDestination, String?) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = WelcomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        welcomeGraph(
            onNewUserClick = {
                onNavigateToDestination(OnBoardingDestination, null)
            },
            onLoginClick = {
                onNavigateToDestination(PhoneNumberDestination, null)
            },
            nestedGraphs = {
                onBoardingGraph {
                    onNavigateToDestination(UserCreateDestination, null)
                }
                phoneNumberGraph {
                    onNavigateToDestination(
                        AccessCodeDestination,
                        AccessCodeDestination.createNavigationRoute(it)
                    )
                }
                userCreateGraph(
                    showAccessCode = {
                        onNavigateToDestination(AccessCodeDestination, null)
                    },
                    onBackClick = onBackClick,
                    onCloseClick = {
                        onNavigateToDestination(OnBoardingDestination, null)
                    }
                )
                accessCodeGraph(
                    showAuthenticated = {
                        onNavigateToDestination(AnimalTypeSelectDestination, null)
                    },
                    onBackClick = onBackClick,
                    onCloseClick = {
                        onNavigateToDestination(OnBoardingDestination, null)
                    }
                )
                animalTypeSelectGraph(
                    showAnimalInfoCreate = {
                        onNavigateToDestination(AnimalCreateDestination, null)
                    },
                    onBackClick = onBackClick,
                    onCloseClick = {
                        onNavigateToDestination(OnBoardingDestination, null)
                    }
                )
                animalCreateGraph(
                    onContinueClick = {
                        onNavigateToDestination(WelcomeToFaunaDestination, null)
                    },
                    onBackClick = onBackClick,
                    onCloseClick = {
                        onNavigateToDestination(OnBoardingDestination, null)
                    }
                )
                welcomeToFaunaGraph {
                    onNavigateToDestination(AnimalDestination, null)
                }
            })
        animalGraph { onNavigateToDestination(AnimalDestination, null) }
    }
}
