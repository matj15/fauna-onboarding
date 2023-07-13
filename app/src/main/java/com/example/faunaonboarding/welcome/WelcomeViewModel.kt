package com.example.faunaonboarding.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val welcomeRepository: WelcomeRepository
) : ViewModel() {

    private val seenOnBoarding = MutableSharedFlow<Boolean>(replay = 1)
    private val completedUserInfo = MutableSharedFlow<Boolean>(replay = 1)

    init {
        val seen = welcomeRepository.seenOnBoarding()
        val completed = welcomeRepository.completedUserInfo()
        seenOnBoarding.tryEmit(seen)
        completedUserInfo.tryEmit(completed)
    }

    fun subscribeSeenOnBoarding() = seenOnBoarding.asSharedFlow()
    fun setSeenOnBoard() = viewModelScope.launch {
        seenOnBoarding.emit(true)
        welcomeRepository.setSeenOnBoarding()
    }

    fun subscribeCompletedUserInfo() = completedUserInfo.asSharedFlow()
    fun setCompletedUserInfo() = viewModelScope.launch {
        completedUserInfo.emit(true)
        welcomeRepository.setCompletedUserInfo()
    }

}