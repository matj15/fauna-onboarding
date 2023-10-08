package com.example.faunaonboarding.createAccount

import androidx.compose.runtime.mutableStateOf
import com.example.faunaonboarding.util.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AccountDataSource @Inject constructor(
    private val user: User
) {
    private val loginSuccessful = mutableStateOf(true)
    private val accessCodeSuccessful = mutableStateOf(true)

    open fun requestLoginCode(phone: String): Flow<Result<Boolean>> {
        return flow { emit(Result.success(accessCodeSuccessful.value)) }
    }

    open fun login(phone: String, accessCode: String): Flow<Result<Boolean>> {
        return flow { emit(Result.success(loginSuccessful.value)) }
    }
}