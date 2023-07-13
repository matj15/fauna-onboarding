package com.example.faunaonboarding.onboarding.userCreate

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faunaonboarding.login.LoginRepository
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
    private val loginRepository: LoginRepository
) : ViewModel() {

    companion object {
        const val phoneNumberLength = 8
    }

    private val _userName = MutableStateFlow("")
    private val _userEmail = MutableStateFlow("")
    private val _userPhoneNumber = MutableStateFlow("")
    private val _userCheckboxChecked = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)

    fun setName(it: String) {
        _userName.value = it
    }

    fun setEmail(it: String) {
        _userEmail.value = it

    }

    fun setPhoneNumber(it: String) {
        _userPhoneNumber.value = it

    }

    fun setCheckboxCheckedStatus(it: Boolean) {
        _userCheckboxChecked.value = it
    }

    fun requestLoginCode() = viewModelScope.launch {
        loginRepository.requestCode(_userPhoneNumber.value)
    }

    fun createUser() {
        /* TODO implement? */
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
    val inputUIState: UserCreateInputUIState = UserCreateInputUIState(
        "",
        "",
        "",
        false
    )
)

data class UserCreateInputUIState(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val checkboxChecked: Boolean,
)

fun UserCreateInputUIState.isNameError(): Boolean {
    return this.name.isBlank()
}

// TODO check if email / phone already exists in DB?
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
