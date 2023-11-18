package com.example.faunaonboarding.onboarding.userCreate

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faunaonboarding.login.PhoneNumberViewModel
import com.example.faunaonboarding.login.PhoneNumberViewModel.Companion.phoneNumberLength
import com.example.faunaonboarding.login.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCreateViewModel @Inject constructor(
    private val userCreateRepository: UserCreateRepository
) : ViewModel() {

    private val _userName = MutableStateFlow("i")
    private val _userEmail = MutableStateFlow("")
    private val _userPhoneNumber = MutableStateFlow("")
    private val _userCheckboxChecked = MutableStateFlow(false)
    private val _uiState = MutableStateFlow<UIState>(UIState.Success)


    fun setName(it: String) {
        _userName.value = _userName.value
//        _userName.value = it
    }

    fun getName() = _userName.value

    fun setEmail(it: String) {
        _userEmail.value = it
    }

    fun getEmail() = _userEmail.value

    fun setPhoneNumber(it: String) {
        if (it.length <= PhoneNumberViewModel.phoneNumberLength) {
            _userPhoneNumber.value = it
        }
    }
    fun getPhoneNumber() = _userPhoneNumber.value

    fun setCheckboxCheckedStatus(it: Boolean) {
        _userCheckboxChecked.value = it
    }

    fun getCheckboxCheckedStatus() = _userCheckboxChecked.value

    fun createUser() = viewModelScope.launch {
        userCreateRepository.userCreate(_userName.value, _userPhoneNumber.value, _userEmail.value)
    }

    val userCreateUiState: StateFlow<UserCreateUIState> =
        combine(
            _userName,
            _userEmail,
            _userPhoneNumber,
            _userCheckboxChecked,
            _uiState
        ) { userName,
            userEmail,
            userPhoneNumber,
            userCheckboxChecked,
            uiState ->
            val userCreateInputUIState = UserCreateInputUIState(
                userName,
                userEmail,
                userPhoneNumber,
                userCheckboxChecked
            )

            UserCreateUIState(
                uiState,
                userCreateInputUIState
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UserCreateUIState()
            )

}

data class UserCreateUIState(
    val result: UIState = UIState.Initial,
    val inputUIState: UserCreateInputUIState = UserCreateInputUIState()
)

data class UserCreateInputUIState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val checkboxChecked: Boolean = false
)

fun UserCreateInputUIState.isNameError(): Boolean {
    return this.name.isBlank()
}

fun UserCreateInputUIState.isEmailError(): Boolean {
    return !Patterns.EMAIL_ADDRESS.matcher(this.email).matches()
}

fun UserCreateInputUIState.isPhoneNumberError(): Boolean {
    return !Patterns.PHONE.matcher(this.phoneNumber).matches()
            || this.phoneNumber.length != phoneNumberLength
}

fun UserCreateInputUIState.continueEnabled(): Boolean {
    return !this.isNameError()
            && !this.isEmailError()
            && !this.isPhoneNumberError()
            && this.checkboxChecked
}
