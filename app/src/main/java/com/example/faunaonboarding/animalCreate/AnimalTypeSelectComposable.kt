package com.example.faunaonboarding.animalCreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.faunaonboarding.compose.components.button.ToggleButton
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space
import com.example.faunaonboarding.util.AnimalType
import com.example.faunaonboarding.util.translationResource

// TODO fetch from database?
val animalTypes = listOf(
    AnimalType.CAT, AnimalType.DOG
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AnimalTypeSelectRoute(
    onAnimalTypeSelected: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    animalCreateViewModel: AnimalCreateViewModel = hiltViewModel()
) {
    val animalCreateUIState by animalCreateViewModel.animalCreateScreenUIState.collectAsStateWithLifecycle()
    val timer by animalCreateViewModel.counter.collectAsStateWithLifecycle()

    if (timer == 0) {
        LaunchedEffect(Unit) {
            onAnimalTypeSelected()
        }
    }

    AnimalTypeSelectComposable(
        animalCreateUIState = animalCreateUIState.inputUIState,
        onAnimalTypeChanged = animalCreateViewModel::setAnimalType,
        onContinueClick = onAnimalTypeSelected,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}

@Composable
fun AnimalTypeSelectComposable(
    modifier: Modifier = Modifier,
    animalCreateUIState: AnimalCreateInputUIState,
    onAnimalTypeChanged: (AnimalType) -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    val animalChosen = animalCreateUIState.animalTypeChosen()

    BackdropScaffoldComposable(peekHeight = 65.dp, appBar = {
        StepCounterTopAppBarComposable(
            screenTitle = stringResource(id = R.string.on_board_new_animal_info_top_appbar_title),
            numberOfSteps = 4,
            stepNumber = 2,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )
    }, frontLayerContent = {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = Space.SPACE32)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = Space.SPACE24),
                    text = stringResource(id = R.string.on_board_new_animal_type_title),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.padding(bottom = Space.SPACE16))
                Text(
                    modifier = Modifier.padding(horizontal = Space.SPACE24),
                    text = stringResource(id = R.string.on_board_new_animal_type_body_text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.padding(bottom = Space.SPACE16))

                Box(
                    modifier = Modifier
                        .padding(horizontal = Space.SPACE24)
                        .weight(0.4F)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ToggleButton(
                            modifier = Modifier
                                .fillMaxWidth(0.5F)
                                .padding(end = Space.SPACE8),
                            image = R.drawable.dog_head,
                            backgroundEllipse = when (animalTypes[0]) {
                                AnimalType.DOG -> R.drawable.ellipse_background_small_top_left
                                else -> R.drawable.ellipse_background_small_bottom_left
                            },
                            backgroundEllipseColor = MaterialTheme.colorScheme.primaryContainer,
                            onClick = {
                                onAnimalTypeChanged(animalTypes[0])
                                onContinueClick()
                            },
                            chosen = animalTypes[0] == animalChosen,
                            text = stringResource(id = animalTypes[0].translationResource())
                        )

                        ToggleButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = Space.SPACE8),
                            image = R.drawable.cat_head,
                            backgroundEllipse = when (animalTypes[1]) {
                                AnimalType.DOG -> R.drawable.ellipse_background_small_top_left
                                else -> R.drawable.ellipse_background_small_bottom_left
                            },
                            backgroundEllipseColor = MaterialTheme.colorScheme.primaryContainer,
                            onClick = {
                                onAnimalTypeChanged(animalTypes[1])
                                onContinueClick()
                            },
                            chosen = animalTypes[1] == animalChosen,
                            text = stringResource(id = animalTypes[1].translationResource())
                        )
                    }
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(2),
//                        contentPadding = PaddingValues(
//                            bottom = Space.SPACE16,
//                            start = Space.SPACE16,
//                            end = Space.SPACE16
//                        ),
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        items(animalTypes.size) { index ->
//                            // TODO make sure it works and displays it correctly
//                            ToggleButton(
//                                image = R.drawable.dog_head,
//                                backgroundEllipse = when (animalTypes[index]) {
//                                    AnimalType.DOG -> R.drawable.ellipse_background_small_top_left
//                                    else -> R.drawable.ellipse_background_small_bottom_left
//                                },
//                                backgroundEllipseColor = MaterialTheme.colorScheme.primaryContainer,
//                                onClick = {
//                                    onAnimalTypeChanged(animalTypes[index])
//                                    onContinueClick()
//                                },
//                                chosen = animalTypes[index] == animalChosen,
//                                text = stringResource(id = animalTypes[index].translationResource())
//                            )
//                        }
//                    }
                }

                Spacer(modifier = Modifier.weight(0.9F))
            }
        }
    })
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun AnimalTypeSelectComposablePreview() {
    FaunaTheme {
        AnimalTypeSelectComposable(
            animalCreateUIState = AnimalCreateInputUIState(AnimalType.CAT, "", "", ""),
            onAnimalTypeChanged = {},
            onContinueClick = {},
            onBackClick = {},
            onCloseClick = {}
        )
    }
}