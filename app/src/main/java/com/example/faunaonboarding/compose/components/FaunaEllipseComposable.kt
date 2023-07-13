package com.example.faunaonboarding.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme

@Composable
fun FaunaEllipseComposable(
    modifier: Modifier = Modifier,
    transformToBottom: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer(rotationY = if (transformToBottom) 180F else 0F)
    ) {
        val rotation = if (transformToBottom) 180F else 0F
        val alignment = if (transformToBottom) Alignment.BottomCenter else Alignment.TopCenter
        Image(
            painterResource(id = R.drawable.ellipse_secondary),
            contentDescription = "ellipse_secondary",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .rotate(rotation)
                .align(alignment)
        )
        Image(
            painterResource(id = R.drawable.ellipse_primary),
            contentDescription = "ellipse_primary",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .rotate(rotation)
                .align(alignment)
        )
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun FaunaEclipseComposablePreview() {
    FaunaTheme {
        FaunaEllipseComposable()
    }
}