package com.example.faunaonboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.example.faunaonboarding.ui.theme.Space

@Composable
fun OtpVerificationCodeRoute(
    showAuthenticated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    OtpVerificationCodeScreenComposable(
        showAuthenticated = showAuthenticated,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun OtpVerificationCodeScreenComposable(
    modifier: Modifier = Modifier,
    showAuthenticated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    viewModel: OtpVerificationCodeViewModel = hiltViewModel()
) {
    val loginScreenUiState by viewModel.loginScreenUiState.collectAsStateWithLifecycle()
    val otpCode = viewModel.otpCode
    val isOtpError = viewModel.isOtpError.value

    if (loginScreenUiState.receivedAccessCode()) {
        LaunchedEffect(Unit) {
            showAuthenticated()
        }
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
                                otpCode.forEachIndexed { index, value ->
                                    OtpDigitInput(
                                        value = value,
                                        onValueChange = {
                                            viewModel.setOtp(index, it)
                                            if (index == otpCode.size - 1) {
                                                val isOtpValid = viewModel.verifyOtp()
                                                if (isOtpValid) {
                                                    showAuthenticated()
                                                } else {
                                                    viewModel.clearOtp()
                                                }
                                            }
                                        },
                                        focusRequester = viewModel.focusRequesters[index],
                                        modifier = Modifier.weight(1f),
                                        isOtpError = isOtpError,
                                        isFocused = index == viewModel.currentFocusIndex.value
                                    )
                                }
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
fun OtpVerificationCodePreview() {
    OtpVerificationCodeScreenComposable(
        showAuthenticated = {},
        onBackClick = {},
        onCloseClick = {})
}