package com.example.faunaonboarding.animalCreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faunaonboarding.R
import com.example.faunaonboarding.compose.components.BackdropScaffoldComposable
import com.example.faunaonboarding.compose.components.ConfettiComposable
import com.example.faunaonboarding.compose.components.StepCounterTopAppBarComposable
import com.example.faunaonboarding.compose.components.button.FaunaButton
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space
import com.example.faunaonboarding.util.AnimalType
import com.example.faunaonboarding.util.drawable

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AnimalCreateRoute(
    onAnimalCreated: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    animalCreateViewModel: AnimalCreateViewModel = hiltViewModel()
) {
    val animalCreateUIState by animalCreateViewModel.animalCreateUIState.collectAsStateWithLifecycle()
    val timer by animalCreateViewModel.counter.collectAsStateWithLifecycle()

    if (timer == 0) {
        LaunchedEffect(Unit) {
            onAnimalCreated()
        }
    }

    val create = AnimalCreateComposable(
        animalCreateInputUIState = animalCreateUIState.inputUIState,
        onNameChanged = animalCreateViewModel::setAnimalName,
        onIdentificationNumberChanged = animalCreateViewModel::setIdentificationNumber,
        onIdentificationNumberConfirmChanged = animalCreateViewModel::setIdentificationNumberConfirm,
        onContinueClick = animalCreateViewModel::createAnimal,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )

    return when (animalCreateUIState.result) {
        UIState.Initial, UIState.Loading -> create
        UIState.Error -> Text(text = stringResource(id = R.string.animal_created_error))
        UIState.Success -> LaunchedEffect(Unit) {
            onAnimalCreated()
        }
    }
}

@Composable
fun AnimalCreateComposable(
    modifier: Modifier = Modifier,
    animalCreateInputUIState: AnimalCreateInputUIState,
    onNameChanged: (String) -> Unit,
    onIdentificationNumberChanged: (String) -> Unit,
    onIdentificationNumberConfirmChanged: (String) -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    BackdropScaffoldComposable(peekHeight = 65.dp, appBar = {
        StepCounterTopAppBarComposable(
            screenTitle = stringResource(id = R.string.on_board_new_animal_info_top_appbar_title),
            numberOfSteps = 4,
            stepNumber = 3,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )
    }, frontLayerContent = {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.weight(0.04F))
                Text(
                    modifier = Modifier.padding(horizontal = Space.SPACE24),
                    text = stringResource(id = R.string.on_board_new_animal_info_title),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.weight(0.012F))
                Text(
                    modifier = Modifier.padding(horizontal = Space.SPACE24),
                    text = stringResource(id = R.string.on_board_new_animal_info_body_text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.weight(0.02F))

                Box(
                    modifier = Modifier
                        .weight(0.7F)
                        .verticalScroll(rememberScrollState())
                ) {
                    AnimalInfoCard(
                        animalCreateInputUIState = animalCreateInputUIState,
                        onNameChanged = onNameChanged,
                        onIdentificationNumberChanged = onIdentificationNumberChanged,
                        onIdentificationNumberConfirmChanged = onIdentificationNumberConfirmChanged,
                        onContinueClick = onContinueClick
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(0.1F)
                        .align(Alignment.CenterHorizontally)
                ) {
                    FaunaButton(
                        modifier = Modifier
                            .fillMaxWidth(0.4F),
                        text = stringResource(id = R.string.on_board_new_user_continue_button),
                        iconRight = R.drawable.right,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        onClick = {
                            if (animalCreateInputUIState.canBeCreated()) onContinueClick()
                        }
                    )
                }
                Spacer(modifier = Modifier.weight(0.06F))
            }
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AnimalInfoCard(
    modifier: Modifier = Modifier,
    animalCreateInputUIState: AnimalCreateInputUIState,
    onNameChanged: (String) -> Unit,
    onIdentificationNumberChanged: (String) -> Unit,
    onIdentificationNumberConfirmChanged: (String) -> Unit,
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
                    .weight(0.3F)
                    .semantics {
                        testTag = "name-field"
                    },
                value = animalCreateInputUIState.name,
                label = { Text(stringResource(R.string.animal_create_name)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusRequester.requestFocus()
                }),
                onValueChange = onNameChanged,
                isError = animalCreateInputUIState.isAnimalNameError(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.weight(0.08F),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        testTag = "chipnumber-field"
                    },
                value = animalCreateInputUIState.identificationNumber,
                label = { Text(stringResource(R.string.animal_create_identification_number)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusRequester.requestFocus()
                }),
                onValueChange = onIdentificationNumberChanged,
                isError = animalCreateInputUIState.isAnimalIdentificationNumberError(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.weight(0.08F),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .semantics {
                        testTag = "chipnumber2-field"
                    },
                value = animalCreateInputUIState.identificationNumberConfirm,
                label = { Text(stringResource(R.string.animal_create_identification_number_confirm)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    if (animalCreateInputUIState.canBeCreated()) {
                        onContinueClick()
                    }
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                }),
                onValueChange = onIdentificationNumberConfirmChanged,
                isError = animalCreateInputUIState.isAnimalIdentificationNumberConfirmError(),
                singleLine = true
            )
            Spacer(modifier = Modifier.weight(0.12F))
        }
    }
}

@Composable
fun AnimalCreatedComposable(
    animalType: AnimalType,
    modifier: Modifier = Modifier
) {
    ConfettiComposable(
        modifier,
        R.string.animal_created_title,
        animalType.drawable()
    )
}

@Preview(showBackground = true, device = Devices.PHONE)
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun AnimalCreateComposablePreview() {
    FaunaTheme {
        AnimalCreateComposable(
            animalCreateInputUIState = AnimalCreateInputUIState(AnimalType.CAT, "", "", ""),
            onNameChanged = {},
            onIdentificationNumberChanged = {},
            onIdentificationNumberConfirmChanged = {},
            onContinueClick = {},
            onBackClick = {}) {

        }
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun AnimalCreatedComposablePreview() {
    FaunaTheme {
        AnimalCreatedComposable(
            AnimalType.DOG
        )
    }
}