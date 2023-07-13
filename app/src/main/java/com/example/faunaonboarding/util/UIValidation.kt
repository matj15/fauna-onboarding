package com.example.faunaonboarding.util

sealed class ValidationUiState {
    object Valid : ValidationUiState()
    object InValid : ValidationUiState()
}