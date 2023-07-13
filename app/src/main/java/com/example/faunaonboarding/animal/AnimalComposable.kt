package com.example.faunaonboarding.animal

import androidx.compose.runtime.Composable

@Composable
fun AnimalRoute(
    showPlayStore: () -> Unit,
    showWelcome: () -> Unit
) {
    AnimalProfileRoute(
        showPlayStore,
        showWelcome
    )
}