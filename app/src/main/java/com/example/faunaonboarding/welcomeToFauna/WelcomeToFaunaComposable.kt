package com.example.faunaonboarding.welcomeToFauna

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.button.FaunaButton
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space

@Composable
fun WelcomeToFaunaRoute(
    onReadyClick: () -> Unit,
) {

    FaunaTheme {
        WelcomeToFaunaScreen(
            onReadyClick = onReadyClick
        )
    }
}

@Composable
fun WelcomeToFaunaScreen(
    onReadyClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onSecondary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(0.17F))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth(0.75F)
            ) {
                Image(
                    painterResource(R.drawable.onboarding_welcome_to_fauna),
                    stringResource(id = R.string.on_board_image),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                )
                Spacer(modifier = Modifier.padding(Space.SPACE32))
                Text(
                    text = stringResource(R.string.on_board_welcome_title),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(Space.SPACE16))
                Text(
                    text = stringResource(id = R.string.on_board_welcome_body),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = Space.SPACE56)
            ) {
                FaunaButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(0.55F),
                    text = stringResource(id = R.string.on_board_welcome_button_title),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    iconRight = R.drawable.right,
                    onClick = onReadyClick
                )

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun OnboardWelcomePreview() {
    FaunaTheme {
        WelcomeToFaunaScreen {}
    }
}