package com.example.faunaonboarding.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.faunaonboarding.compose.components.FaunaEllipseComposable
import com.example.faunaonboarding.compose.components.button.FaunaButton
import com.example.faunaonboarding.ui.theme.FaunaTheme

@Composable
fun WelcomeRoute(
    onNewUserClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    SignUpOrLoginScreen(
        onNewUserClick = onNewUserClick,
        onLoginClick = onLoginClick
    )
}

@Composable
fun SignUpOrLoginScreen(
    onNewUserClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5F)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.55F)
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Spacer(modifier = Modifier.weight(0.13F))
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.fauna_logo_solid_green),
                        contentDescription = stringResource(id = R.string.fauna_logo_solid_green),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.weight(0.03F))
                    Text(
                        modifier = Modifier
                            .weight(0.1F),
                        text = stringResource(id = R.string.registration_or_login_title),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .weight(0.5F)
            ) {
                FaunaEllipseComposable(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    transformToBottom = true
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Spacer(modifier = Modifier.weight(0.7F))
                    FaunaButton(
                        modifier = Modifier
                            .fillMaxWidth(0.55F),
                        text = stringResource(id = R.string.register_new_user_button_title),
                        backgroundColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onClick = onNewUserClick
                    )
                    Spacer(modifier = Modifier.weight(0.01F))
                    FaunaButton(
                        modifier = Modifier
                            .fillMaxWidth(0.55F),
                        text = stringResource(id = R.string.login_button_title),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        onClick = onLoginClick
                    )
                    Spacer(modifier = Modifier.weight(0.25F))
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun SignUpOrLoginPreview() {
    FaunaTheme {
        SignUpOrLoginScreen({}, {})
    }
}