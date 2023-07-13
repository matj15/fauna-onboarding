package com.example.faunaonboarding.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme

@Composable
fun PhoneNumberRoute(showLogin: (String) -> Unit) {
    PhoneNumberScreenComposable(
        showLogin
    )
}


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PhoneNumberScreenComposable(
    showLogin: (String) -> Unit,
    viewModel: PhoneNumberViewModel = hiltViewModel()
) {
    val phoneNumberScreenUiState by viewModel.phoneNumberScreenUiState.collectAsStateWithLifecycle()
    val phoneNumber by viewModel.phoneNumber.collectAsStateWithLifecycle()

    if (phoneNumberScreenUiState.receivedAccessCode()) {
        LaunchedEffect(Unit) {
            showLogin(phoneNumber)
        }
    }

    PhoneNumberComposable(
        phoneNumberScreenUiState,
        phoneNumber,
        onPhoneNumberValueChanged = viewModel::setPhoneNumber,
        onRequestedLoginCode = viewModel::requestLoginCode
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberComposable(
    phoneNumberScreenUiState: PhoneNumberScreenUiState,
    phoneNumber: String,
    onPhoneNumberValueChanged: (String) -> Unit,
    onRequestedLoginCode: () -> Unit, //TODO Figure out if we are supposed to do it like this?
) {
    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(R.string.phone_number_title),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.phone_number_body),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberValueChanged,
                label = { Text(stringResource(R.string.phone_number_phone_number_hint)) },
                isError = phoneNumberScreenUiState.isError(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(onAny = {
                    onRequestedLoginCode()
                }),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = onRequestedLoginCode,
                enabled =  phoneNumberScreenUiState.submitEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(stringResource(id = R.string.phone_number_button))
            }
        }

    }
}


@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun PhoneNumberComposablePreview() {
    var phoneNumber by remember { mutableStateOf("") }
    val phoneNumberScreenUiState = PhoneNumberScreenUiState()

    FaunaTheme {
        PhoneNumberComposable(phoneNumberScreenUiState, phoneNumber, {}, {})
    }
}