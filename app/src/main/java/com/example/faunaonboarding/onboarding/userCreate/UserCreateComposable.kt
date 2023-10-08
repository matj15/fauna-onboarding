package com.example.faunaonboarding.onboarding.userCreate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.BackdropScaffoldComposable
import com.example.faunaonboarding.compose.components.StepCounterTopAppBarComposable
import com.example.faunaonboarding.compose.components.UserInfoCard
import com.example.faunaonboarding.compose.components.button.FaunaButton
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UserCreateRoute(
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    userCreateViewModel: UserCreateViewModel = hiltViewModel()
) {
    val userCreateScreenUiState by userCreateViewModel.userCreateUiState.collectAsStateWithLifecycle()

    UserCreateComposable(
        userCreateInputUIState = userCreateScreenUiState.inputUIState,
        onNameChanged = userCreateViewModel::setName,
        onEmailChanged = userCreateViewModel::setEmail,
        onPhoneNumberChanged = userCreateViewModel::setPhoneNumber,
        onCheckboxCheckedChanged = userCreateViewModel::setCheckboxCheckedStatus,
        onContinueClick = {
            if (userCreateScreenUiState.inputUIState.continueEnabled()) {
                userCreateViewModel.createUser()
                onContinueClick()
            }
        },
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}

@Composable
fun UserCreateComposable(
    modifier: Modifier = Modifier,
    userCreateInputUIState: UserCreateInputUIState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onCheckboxCheckedChanged: (Boolean) -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    BackdropScaffoldComposable(peekHeight = 65.dp,
        appBar = {
            StepCounterTopAppBarComposable(
                screenTitle = stringResource(id = R.string.on_board_new_user_info_top_appbar_title),
                numberOfSteps = 4,
                stepNumber = 0,
                onBackClick = onBackClick,
                onCloseClick = onCloseClick
            )
        },
        frontLayerContent = {
            Box(modifier = modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Spacer(modifier = Modifier.weight(0.04F))
                    Text(
                        modifier = Modifier.padding(horizontal = Space.SPACE24),
                        text = stringResource(id = R.string.on_board_new_user_info_title),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.weight(0.012F))
                    Text(
                        modifier = Modifier.padding(horizontal = Space.SPACE24),
                        text = stringResource(id = R.string.on_board_new_user_info_body_text),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.weight(0.02F))

                    Box(
                        modifier = Modifier
                            .weight(0.7F)
                            .verticalScroll(rememberScrollState())
                    ) {
                        UserInfoCard(
                            userCreateInputUIState = userCreateInputUIState,
                            onNameChanged = onNameChanged,
                            onEmailChanged = onEmailChanged,
                            onPhoneNumberChanged = onPhoneNumberChanged,
                            onCheckboxCheckedChanged = onCheckboxCheckedChanged,
                            onContinueClick = onContinueClick
                        )
                    }
                    Box(modifier = Modifier
                        .weight(0.1F)
                        .align(Alignment.CenterHorizontally)) {
                        FaunaButton(
                            modifier = Modifier
                                .fillMaxWidth(0.4F),
                            text = stringResource(id = R.string.on_board_new_user_continue_button),
                            iconRight = R.drawable.right,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            onClick = {
                                if (userCreateInputUIState.continueEnabled()) onContinueClick()
                            }
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.06F))
                }
            }
        })
}


@Preview(showBackground = true, device = Devices.PHONE)
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun UserCreateComposablePreview() {
    FaunaTheme {
        UserCreateComposable(
            userCreateInputUIState = UserCreateInputUIState("", "", "", false),
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneNumberChanged = {},
            onCheckboxCheckedChanged = {},
            onContinueClick = {},
            onBackClick = {},
            onCloseClick = {})
    }
}