package com.example.faunaonboarding.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.faunaonboarding.R
import com.example.faunaonboarding.onboarding.userCreate.PrivacyPolicyText
import com.example.faunaonboarding.onboarding.userCreate.UserCreateInputUIState
import com.example.faunaonboarding.onboarding.userCreate.continueEnabled
import com.example.faunaonboarding.onboarding.userCreate.isEmailError
import com.example.faunaonboarding.onboarding.userCreate.isNameError
import com.example.faunaonboarding.onboarding.userCreate.isPhoneNumberError
import com.example.faunaonboarding.ui.theme.Space

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    userCreateInputUIState: UserCreateInputUIState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onCheckboxCheckedChanged: (Boolean) -> Unit,
    onContinueClick: () -> Unit,
) {
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Space.SPACE16, vertical = Space.SPACE8)
            .height(IntrinsicSize.Min),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Space.SPACE16),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO substitute textFields with pre-made custom ones
            Spacer(
                modifier = Modifier.weight(0.12F),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3F),
                value = userCreateInputUIState.name,
                label = { Text(stringResource(R.string.user_create_name)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusRequester.requestFocus()
                }),
                onValueChange = onNameChanged,
                isError = userCreateInputUIState.isNameError(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.weight(0.08F),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = userCreateInputUIState.email,
                label = { Text(stringResource(R.string.user_create_email)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusRequester.requestFocus()
                }),
                onValueChange = onEmailChanged,
                isError = userCreateInputUIState.isEmailError(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.weight(0.08F),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = userCreateInputUIState.phoneNumber,
                label = { Text(stringResource(R.string.user_create_phone_number)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    if (userCreateInputUIState.continueEnabled()) {
                        onContinueClick()
                    }
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                }),
                onValueChange = onPhoneNumberChanged,
                isError = userCreateInputUIState.isPhoneNumberError(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.weight(0.04F),
            )
            // TODO unsure how to align it with inputs?
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = userCreateInputUIState.checkboxChecked,
                    onCheckedChange = onCheckboxCheckedChanged,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.secondary
                    )
                )
                PrivacyPolicyText()
            }
            Spacer(
                modifier = Modifier.weight(0.06F),
            )
        }
    }
}