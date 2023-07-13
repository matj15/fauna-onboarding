package com.example.faunaonboarding.compose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.faunaonboarding.ui.theme.secondaryColors

@Composable
fun StepsProgressBar(numberOfSteps: Int, currentStep: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0 until numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isCurrent = step == currentStep
            )
        }
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isCurrent: Boolean) {
    val color = if (isCurrent) MaterialTheme.colorScheme.secondary else secondaryColors.C40

    Box(modifier = modifier) {
        //Circle
        Canvas(modifier = Modifier
            .height(3.dp)
            .width(10.dp)
            .align(Alignment.Center)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = color)
            }
        )
    }
}

@Preview
@Composable
fun StepsProgressBarPreview() {
    val currentStep = remember { mutableStateOf(1) }
    StepsProgressBar(
        numberOfSteps = 5,
        currentStep = currentStep.value
    )
}