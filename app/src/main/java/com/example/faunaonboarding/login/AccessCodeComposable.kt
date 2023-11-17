package com.example.faunaonboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.BackdropScaffoldComposable
import com.example.faunaonboarding.compose.components.StepCounterTopAppBarComposable
import com.example.faunaonboarding.ui.theme.Space

@Composable
fun AccessCodeRoute(
    showAuthenticated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    AccessCodeScreenComposable(
        showAuthenticated = showAuthenticated,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}


@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AccessCodeScreenComposable(
    modifier: Modifier = Modifier,
    showAuthenticated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    viewModel: AccessCodeViewModel = hiltViewModel()
) {
    val loginScreenUiState by viewModel.loginScreenUiState.collectAsStateWithLifecycle()
    val accessCode = viewModel.getAccessCode()
    val isError = viewModel.isOtpError.value

    if (loginScreenUiState.isAuthenticated()) {
        LaunchedEffect(Unit) {
            showAuthenticated()
        }
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackdropScaffoldComposable(peekHeight = 65.dp,
        appBar = {
            StepCounterTopAppBarComposable(
                screenTitle = stringResource(id = R.string.on_board_otp_code_top_appbar_title),
                numberOfSteps = 4,
                stepNumber = 1,
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
                        text = stringResource(id = R.string.on_board_otp_code_title),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.weight(0.012F))
                    Text(
                        modifier = Modifier.padding(horizontal = Space.SPACE24),
                        text = stringResource(id = R.string.on_board_otp_code_body_text),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.weight(0.15F))

                    Box(
                        modifier = Modifier
                            .weight(0.7F)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier
                                    .fillMaxWidth(0.75F)
                            ) {
//                                accessCode.forEachIndexed { index, value ->
//                                    OtpDigitInput(
//                                        value = value,
//                                        onValueChange = {
//                                            viewModel.setOtp(index, it)
//                                            if (index == otpCode.size - 1) {
//                                                val isOtpValid = viewModel.verifyOtp()
//                                                if (isOtpValid) {
//                                                    showAuthenticated()
//                                                } else {
//                                                    viewModel.clearOtp()
//                                                }
//                                            }
//                                        },
//                                        focusRequester = viewModel.focusRequesters[index],
//                                        modifier = Modifier.weight(1f),
//                                        isOtpError = isOtpError,
//                                        isFocused = index == viewModel.currentFocusIndex.value
//                                    )
//                                }
                                OutlinedTextField(
                                    value = TextFieldValue(
                                        accessCode,
                                        TextRange(accessCode.length)
                                    ),
                                    onValueChange = {
                                        viewModel.setAccessCode(it.text)
                                        if (AccessCodeViewModel.accessCodeLength == it.text.length) {
                                            showAuthenticated()
                                        }
                                    },
                                    label = { Text(stringResource(R.string.access_code_hint)) },
                                    isError = loginScreenUiState.isError(),
                                    enabled = loginScreenUiState.isEnabled(),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Send
                                    ),
                                    keyboardActions = KeyboardActions(onAny = {
//                                        viewModel.authenticate()
                                        showAuthenticated()
                                    }
                                    ),
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .focusRequester(focusRequester)
                                )
                            }
                        }
                    }
                }
            }
        })
}

@Preview(showBackground = true, device = Devices.PHONE)
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun AccessCodePreview() {
    AccessCodeScreenComposable(
        showAuthenticated = {},
        onBackClick = {},
        onCloseClick = {})
}