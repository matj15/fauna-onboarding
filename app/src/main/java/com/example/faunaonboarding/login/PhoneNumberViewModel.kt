package com.example.faunaonboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faunaonboarding.util.ValidationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    companion object {
        const val phoneNumberLength = 8
    }

    private val _phoneNumberFlow = MutableStateFlow("")

    val phoneNumber: StateFlow<String> = _phoneNumberFlow.asStateFlow()

    fun setPhoneNumber(it: String) {
        if (it.length <= phoneNumberLength) {
            _phoneNumberFlow.value = it
        }
    }

    fun requestLoginCode() = viewModelScope.launch {
        loginRepository.requestCode(_phoneNumberFlow.value)
    }

    private val phoneNumberValidationUiState: StateFlow<ValidationUiState> =
        _phoneNumberFlow
            .map {
                if (it.length == 8) {
                    return@map ValidationUiState.Valid
                }
                return@map ValidationUiState.InValid
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ValidationUiState.InValid
            )

    private val getRequestCodeFlow: StateFlow<UIState> =
        loginRepository.getRequestCodeFlow
            .map { result ->
                when (result) {
//                    com.example.faunaonboarding.util.Result.Loading -> UIState.Loading
//                    is Result.Success -> UIState.Success
//                    is Result.Error -> UIState.Error
                    Result.success(true) -> UIState.Success
                    Result.success(false) -> UIState.Loading
                    else -> UIState.Error
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UIState.Initial
            )

    val phoneNumberScreenUiState: StateFlow<PhoneNumberScreenUiState> =
        combine(
            getRequestCodeFlow,
            phoneNumberValidationUiState,
        ) { requestPhoneNumberUiState, phoneNumberValidation ->
            PhoneNumberScreenUiState(
                requestPhoneNumberUiState,
                phoneNumberValidation
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PhoneNumberScreenUiState()
            )
}

sealed interface UIState {
    object Initial : UIState
    object Success : UIState
    object Error : UIState
    object Loading : UIState
}

data class PhoneNumberScreenUiState(
    val requestPhoneNumber: UIState = UIState.Initial,
    val phoneNumberValidation: ValidationUiState = ValidationUiState.InValid
)

fun PhoneNumberScreenUiState.submitEnabled(): Boolean {
    return when (this.requestPhoneNumber) {
        UIState.Loading, UIState.Success -> false
        UIState.Error -> true
        UIState.Initial -> phoneNumberValidation is ValidationUiState.Valid
    }
}

fun PhoneNumberScreenUiState.isError(): Boolean {
    return when (this.requestPhoneNumber) {
        UIState.Error -> true
        else -> false
    }
}

fun PhoneNumberScreenUiState.receivedAccessCode(): Boolean {
    return this.requestPhoneNumber is UIState.Success
}