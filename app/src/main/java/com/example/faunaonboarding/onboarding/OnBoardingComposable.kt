package com.example.faunaonboarding.onboarding

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.StepsProgressBar
import com.example.faunaonboarding.compose.components.button.FaunaButton
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.welcome.WelcomeViewModel

data class OnBoardingStep(val title: Int, val body: Int, val image: Int)

val onBoardingSteps = listOf(
    OnBoardingStep(
        R.string.on_board_title_1,
        R.string.on_board_body_1,
        R.drawable.onboarding_digital_vaccines
    ),
    OnBoardingStep(
        R.string.on_board_title_2,
        R.string.on_board_body_2,
        R.drawable.onboarding_get_rid_of_physical_documents
    ),
    OnBoardingStep(
        R.string.on_board_title_3,
        R.string.on_board_body_3,
        R.drawable.onboarding_household_overview
    ),
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun OnBoardingRoute(
    navigateToNewUserInfoRegistration: () -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val currentStep = rememberSaveable { mutableStateOf(0) }
    val seenOnBoardingState by welcomeViewModel.subscribeSeenOnBoarding()
        .collectAsStateWithLifecycle(false)

    if (seenOnBoardingState) {
        LaunchedEffect(seenOnBoardingState) {
            navigateToNewUserInfoRegistration()
        }
    }

    FaunaTheme {
        OnBoardingScreen(
            step = onBoardingSteps[currentStep.value],
            stepNumber = currentStep.value,
            numberOfSteps = onBoardingSteps.size,
            onBackClick = {
                if (currentStep.value != 0) {
                    currentStep.value = currentStep.value - 1
                }
            }) {
            if (currentStep.value == onBoardingSteps.size - 1) {
                welcomeViewModel.setSeenOnBoard()
            } else {
                currentStep.value = currentStep.value + 1
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    step: OnBoardingStep,
    stepNumber: Int,
    numberOfSteps: Int,
    onBackClick: () -> Unit,
    onGetStartedClick: () -> Unit
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
            Spacer(modifier = Modifier.weight(0.1F))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.8F)
                    .fillMaxWidth(0.75F)
            ) {
                Image(
                    painterResource(step.image),
                    stringResource(id = R.string.on_board_image),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2F)
                )
                Spacer(modifier = Modifier.weight(0.02F))
                Text(
                    text = stringResource(step.title),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(0.01F))
                Text(
                    modifier = Modifier.weight(0.1F),
                    text = stringResource(id = step.body),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(0.2F)
            ) {
                Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                    StepsProgressBar(
                        numberOfSteps,
                        stepNumber,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .weight(0.05F)
                            .fillMaxWidth(0.1F)
                    )
                    FaunaButton(
                        text = stringResource(id = R.string.on_board_get_started),
                        modifier = Modifier
                            .fillMaxWidth(0.55F),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        onClick = onGetStartedClick
                    )
                    Spacer(modifier = Modifier.weight(0.03F))
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun DefaultPreview() {
    val currentStep = remember { mutableStateOf(0) }

    FaunaTheme {
        OnBoardingScreen(
            onBoardingSteps[currentStep.value],
            currentStep.value,
            onBoardingSteps.size,
            {}) {
            currentStep.value = currentStep.value++
        }
    }
}