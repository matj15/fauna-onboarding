package com.example.faunaonboarding.compose.components

import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackdropScaffoldComposable(
    appBar: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    headerHeight: Dp = 200.dp,
    peekHeight: Dp = 80.dp
) {
    BackdropScaffold(
        appBar = appBar,
        backLayerContent = {

        },
        frontLayerContent = frontLayerContent,
        headerHeight = headerHeight,
        peekHeight = peekHeight,
        backLayerBackgroundColor = MaterialTheme.colorScheme.onSecondary,
        frontLayerShape = MaterialTheme.shapes.extraLarge,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        gesturesEnabled = false
    )
}