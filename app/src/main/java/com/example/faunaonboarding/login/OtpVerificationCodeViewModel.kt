package com.example.faunaonboarding.login

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faunaonboarding.login.LoginRepository
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.util.ValidationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationCodeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _otpCodeFlowList = mutableStateListOf("", "", "", "")
    val otpCode: List<String> = _otpCodeFlowList
    // TODO modify to only user StateFlow
    private val _otpCodeFlow = MutableStateFlow("0000")


    var isOtpError = mutableStateOf(false)

    val focusRequesters = List(otpCodeLength) { FocusRequester() }
    var currentFocusIndex = mutableStateOf(0)

    companion object {
        const val otpCodeLength = 4
    }

    private val _phoneNumber: String = checkNotNull(
        savedStateHandle[OtpVerificationCodeDestination.phoneNumberArg]
    )

    fun setOtp(index: Int, value: String) {
        if (index >= 0 && index < otpCode.size) {
            _otpCodeFlowList[index] = value

            // Move focus to the next field when a digit is entered
            if (index < _otpCodeFlowList.size - 1 && value.isNotBlank()) {
                currentFocusIndex.value = index + 1
                focusRequesters[currentFocusIndex.value].requestFocus()
            }
        }
    }

    fun clearOtp() {
        _otpCodeFlowList.fill("0")
        currentFocusIndex.value = 0
        focusRequesters[currentFocusIndex.value].requestFocus()

    }

    fun verifyOtp(): Boolean {
        val enteredOtp = otpCode.joinToString("")
        // TODO verification logic
        isOtpError.value = enteredOtp != "1234"
        return enteredOtp == "1234"
    }

    fun login() = viewModelScope.launch {
//        loginRepository.login(_phoneNumber, otpCode.joinToString("")).await()
        loginRepository.login(_phoneNumber, otpCode.joinToString(""))
    }

    private val otpVerificationCodeValidationUiState: StateFlow<ValidationUiState> =
        _otpCodeFlow
            .map {
                if (it.length == otpCodeLength) {
                    return@map ValidationUiState.Valid
                }
                return@map ValidationUiState.InValid
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ValidationUiState.InValid
            )

    private val getLoginUiState: StateFlow<UIState> =
        loginRepository.getLoginFlow
            .map { result ->
                when (result) {
                    // TODO loading?
                    Result.success(true) -> UIState.Success
                    Result.success(false) -> UIState.Error
                    else -> UIState.Loading
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UIState.Initial
            )

    val loginScreenUiState: StateFlow<OtpVerificationCodeScreenUiState> =
        combine(
            getLoginUiState,
            otpVerificationCodeValidationUiState,
        ) { loginUIState, phoneNumberValidation ->
            OtpVerificationCodeScreenUiState(
                loginUIState,
                phoneNumberValidation
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = OtpVerificationCodeScreenUiState()
            )
}

data class OtpVerificationCodeScreenUiState(
    val login: UIState = UIState.Initial,
    val phoneNumberValidation: ValidationUiState = ValidationUiState.InValid
)

fun OtpVerificationCodeScreenUiState.submitEnabled(): Boolean {
    return when (this.login) {
        UIState.Loading, UIState.Success -> false
        UIState.Error -> true
        UIState.Initial -> phoneNumberValidation is ValidationUiState.Valid
    }
}

fun OtpVerificationCodeScreenUiState.isError(): Boolean {
    return when (this.login) {
        UIState.Error -> true
        else -> false
    }
}

fun OtpVerificationCodeScreenUiState.receivedAccessCode(): Boolean {
    return this.login is UIState.Success
}