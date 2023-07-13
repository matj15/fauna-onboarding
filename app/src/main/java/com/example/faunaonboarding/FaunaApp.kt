package com.example.faunaonboarding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.faunaonboarding.compose.FaunaAppState
import com.example.faunaonboarding.compose.navigation.BottomNavigationComposable
import com.example.faunaonboarding.compose.navigation.FaunaNavHost
import com.example.faunaonboarding.compose.rememberFaunaAppState
import com.example.faunaonboarding.ui.theme.FaunaTheme

@Composable
fun FaunaApp(
    appState: FaunaAppState = rememberFaunaAppState()
) {
    FaunaTheme {
        //TODO Add fauna background here
        //TODO Add TopBar here
        Scaffold(
            contentColor = MaterialTheme.colors.onBackground,
            bottomBar = {
                if (appState.shouldShowBottomBar(appState.currentDestination)) {
                    BottomNavigationComposable(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigate,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        ) { paddingValues ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
                    .padding(paddingValues)
            ) {
                FaunaNavHost(
                    navController = appState.navController,
                    onBackClick = appState::onBackClick,
                    onNavigateToDestination = appState::navigate,
                )
            }
        }
    }

}