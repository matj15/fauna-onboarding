package com.example.faunaonboarding.animal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AnimalProfileRoute(
    showPlayStore: () -> Unit,
    showWelcome: () -> Unit
//    animalProfileViewModel : AnimalProfileViewModel = hiltViewModel()
) {
//    val updateAppUIState by animalProfileViewModel.updateUIState.collectAsStateWithLifecycle()
//    val userUIState by animalProfileViewModel.userUIState.collectAsStateWithLifecycle()
//    val vaccinationCardUIStates by animalProfileViewModel.vaccinationCardUIStates.collectAsStateWithLifecycle()
//
//    if (updateAppUIState is UpdateAppUIState.Update) {
//        UpdateAppComposable(
//            showPlayStore
//        )
//    }
//
//    if(userUIState is UserUIState.Error) {
//        LaunchedEffect(Unit) {
//            showWelcome()
//        }
//    }
//    else if(vaccinationCardUIStates.animalUIState is AnimalUIState.NoAnimal) {
//        LaunchedEffect(Unit) {
//            showCreateAnimal()
//        }
//    }

    AnimalProfileScreen(
//        animalUIState = vaccinationCardUIStates.animalUIState,
//        showEditAnimal = showEditAnimal
    )
}

@Composable
fun AnimalProfileScreen(
//    animalUIState: AnimalUIState,
//    showEditAnimal: (animalId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(R.color.green_200))
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Welcome to Fauna!")
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun AnimalProfileScreenPreview() {
    FaunaTheme {
        AnimalProfileScreen(
        )
    }
}
