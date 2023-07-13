package com.example.faunaonboarding.animalCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Input
import com.example.faunaonboarding.animal.AnimalRepository
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.util.AnimalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalCreateViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _animalType = MutableStateFlow(AnimalType.DOG)
    private val _animalName = MutableStateFlow("")
    private val _identificationNumber = MutableStateFlow("")
    private val _identificationNumberConfirm = MutableStateFlow("")

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)

    fun setAnimalType(type: AnimalType) {
        _animalType.value = type
    }

    fun setAnimalName(it: String) {
        _animalName.value = it
    }

    fun setIdentificationNumber(it: String) {
        _identificationNumber.value = it
    }

    fun setIdentificationNumberConfirm(it: String) {
        _identificationNumberConfirm.value = it
    }

    fun createAnimal() = viewModelScope.launch {
        _uiState.emit(UIState.Loading)
        animalRepository.animalCreate(
            AnimalCreate(
                type = _animalType.value,
                name = _animalName.value,
                identificationNumber = Input.optional(_identificationNumber.value)
            )
        )
//            .collect { res ->
//            when (res) {
////                Result.Loading -> _uiState.emit(UIState.Loading)
////                is Result.Error -> _uiState.emit(UIState.Error)
////                is Result.Success -> {
////                    _uiState.emit(UIState.Success)
////                }
//                Result.success(true) -> UIState.Success
//                Result.success(false) -> UIState.Error
//                else -> UIState.Error
//            }
//        }
    }

    val animalCreateUIState: StateFlow<AnimalCreateUIState> = combine(
        _animalType,
        _animalName,
        _identificationNumber,
        _identificationNumberConfirm,
        _uiState
    ) { animalType, animalName, identificationNumber, identificationNumberConfirm, uiState ->
        val animalCreateInputUIState = AnimalCreateInputUIState(
            animalType,
            animalName,
            identificationNumber,
            identificationNumberConfirm
        )
        if (uiState is UIState.Success) {
            startTimer()
        }

        AnimalCreateUIState(
            uiState,
            animalCreateInputUIState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AnimalCreateUIState()
    )

    private val _counter = MutableStateFlow(-1)

    val counter: StateFlow<Int> = _counter

    private fun startTimer() = viewModelScope.launch {
        initTimer()
            .onCompletion { _counter.value = 0 }
            .collect()
    }

    private fun initTimer(): Flow<Int> = (totalSeconds - 1 downTo 0).asFlow()
        .onEach { delay(1000) }
        .onStart { emit(totalSeconds) } // Emit total seconds immediately
        .conflate() // In case the creating of State takes some time, conflate keeps the time ticking separately
        .transform { remainingSeconds: Int ->
            emit(remainingSeconds)
        }

    companion object {
        private const val totalSeconds = 3
    }
}

data class AnimalCreateUIState(
    val result: UIState = UIState.Initial,
    val inputUIState: AnimalCreateInputUIState = AnimalCreateInputUIState(
        AnimalType.DOG,
        "",
        "",
        ""
    )
)

data class AnimalCreateInputUIState(
    val type: AnimalType,
    val name: String,
    val identificationNumber: String,
    val identificationNumberConfirm: String,
)

fun AnimalCreateInputUIState.animalTypeChosen(): AnimalType {
    return this.type
}

fun AnimalCreateInputUIState.isAnimalNameError(): Boolean {
    return this.name.isBlank()
}

// TODO check if ID already exists in DB?
fun AnimalCreateInputUIState.isAnimalIdentificationNumberError(): Boolean {
    return this.identificationNumber.isBlank()
}

fun AnimalCreateInputUIState.isAnimalIdentificationNumberConfirmError(): Boolean {
    return this.identificationNumber != this.identificationNumberConfirm
            || this.identificationNumberConfirm.isBlank()
}

fun AnimalCreateInputUIState.canBeCreated(): Boolean {
    return !this.isAnimalNameError()
            && !this.isAnimalIdentificationNumberError()
            && !this.isAnimalIdentificationNumberConfirmError()
}