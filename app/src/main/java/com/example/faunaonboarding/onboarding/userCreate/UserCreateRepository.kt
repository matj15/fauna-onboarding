package com.example.faunaonboarding.onboarding.userCreate

import com.example.faunaonboarding.createAccount.AccountDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class UserCreateRepository @Inject constructor(
    private val accountDataSource: AccountDataSource,
    coroutineContext: CoroutineContext = Dispatchers.IO
) {
    private val scope = CoroutineScope(coroutineContext)

    private val createUserFlow = MutableSharedFlow<Result<Boolean>>()

    val getCreateUserFlow: Flow<Result<Boolean>> = createUserFlow

    fun userCreate(name: String, phone: String, username: String) = scope.launch {
        accountDataSource.userCreate(name, phone, username).collect {
            createUserFlow.emit(it)
        }
    }
}