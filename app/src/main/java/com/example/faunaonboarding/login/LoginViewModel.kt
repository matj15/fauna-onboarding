package com.example.faunaonboarding.login

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faunaonboarding.util.ValidationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccessCodeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _otpCodeFlowList = mutableStateListOf("", "", "", "")
    val otpCode: List<String> = _otpCodeFlowList
    // TODO modify to only user StateFlow
    private val _otpCodeFlow = MutableStateFlow("0000")

    private val _accessCodeFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val _currentFocusIndex: MutableStateFlow<Int> = MutableStateFlow(0)


    var isOtpError = mutableStateOf(false)

    val focusRequesters = List(accessCodeLength) { FocusRequester() }
    var currentFocusIndex = mutableStateOf(0)

    companion object {
        const val accessCodeLength = 4
    }

    private val _phoneNumber: String = checkNotNull(
        savedStateHandle[AccessCodeDestination.phoneNumberArg]
    )

//    fun setOtp(index: Int, value: String) {
//        if (index >= 0 && index < otpCode.size) {
//            _otpCodeFlowList[index] = value
//
//            // Move focus to the next field when a digit is entered
//            if (index < _otpCodeFlowList.size - 1 && value.isNotBlank()) {
//                currentFocusIndex.value = index + 1
//                focusRequesters[currentFocusIndex.value].requestFocus()
//            }
//        }
//    }

    fun setAccessCode(value: String) {
        _accessCodeFlow.value = value
//        if (_accessCodeFlow.value.length == accessCodeLength) {
//            authenticate()
//        }
    }

    fun getAccessCode(): String {
        return _accessCodeFlow.value
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

    fun authenticate() = viewModelScope.launch {
        loginRepository.login(_phoneNumber, _accessCodeFlow.value).await()
    }

    private val otpVerificationCodeValidationUiState: StateFlow<ValidationUiState> =
        _otpCodeFlow
            .map {
                if (it.length == accessCodeLength) {
                    return@map ValidationUiState.Valid
                }
                return@map ValidationUiState.InValid
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ValidationUiState.InValid
            )

    private val _loginUiStateFlow: StateFlow<UIState> =
        loginRepository.accessCode
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

    val loginScreenUiState: StateFlow<AccessCodeScreenUiState> =
        combine(
            _loginUiStateFlow,
            _accessCodeFlow,
            _currentFocusIndex
        ) { loginUIState, phoneNumberValidation, currentFocusIndex ->
            AccessCodeScreenUiState(
                loginUIState,
                phoneNumberValidation,
                currentFocusIndex
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AccessCodeScreenUiState()
            )
}

//data class AccessCodeScreenUiState(
//    val login: UIState = UIState.Initial,
//    val phoneNumberValidation: ValidationUiState = ValidationUiState.InValid
//)

data class AccessCodeScreenUiState(
    val login: UIState = UIState.Initial,
    val accessCode: String = "",
    val currentAccessCodeIndex: Int = 0
)

fun AccessCodeScreenUiState.isEnabled(): Boolean {
    return when (this.login) {
        UIState.Loading, UIState.Success -> false
        else -> true
    }
}

fun AccessCodeScreenUiState.isError(): Boolean {
    return when (this.accessCode) {
        "1234" -> false
        else -> true
    }
}

fun AccessCodeScreenUiState.isAuthenticated(): Boolean {
    return this.login is UIState.Success
}