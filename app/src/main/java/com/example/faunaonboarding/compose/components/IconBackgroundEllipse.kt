package com.example.faunaonboarding.compose.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.primaryColors

@Composable
fun IconBackgroundEllipse(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    tint: Color = MaterialTheme.colorScheme.secondaryContainer,
    backgroundEllipse: Int
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Icon(
            painterResource(id = backgroundEllipse),
            contentDescription = "IconBackground",
            tint = tint,
            modifier = Modifier
                .fillMaxSize(0.8F)
        )

        Icon(
            painterResource(id = image),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .fillMaxSize(0.5F)
        )
    }
}

@Preview
@Composable
fun IconBackgroundEllipsePreview() {
    FaunaTheme {
        IconBackgroundEllipse(
            image = R.drawable.cat_head,
            tint = primaryColors.C40,
            backgroundEllipse = R.drawable.background_ellipse
        )
    }
}
