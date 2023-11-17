package com.example.faunaonboarding.compose.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.IconBackgroundEllipse
import com.example.faunaonboarding.ui.theme.FaunaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToggleButton(
    image: Int,
    backgroundEllipse: Int,
    backgroundEllipseColor: Color,
    onClick: () -> Unit,
    chosen: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
//            .padding(10.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (chosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(0.1F))

            IconBackgroundEllipse(
                image = image,
                tint = backgroundEllipseColor,
                backgroundEllipse = backgroundEllipse,
                modifier = Modifier.weight(0.6f)
            )
            Text(
                text = text,
                color = if (chosen) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(0.2f)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun ToggleButtonPreview() {
    FaunaTheme {
        ToggleButton(
            image = R.drawable.dog_head,
            backgroundEllipse = R.drawable.ellipse_background_small_top_left,
            backgroundEllipseColor = MaterialTheme.colorScheme.primaryContainer,
            onClick = {},
            chosen = false,
            text = stringResource(id = R.string.dog)
        )
    }
}