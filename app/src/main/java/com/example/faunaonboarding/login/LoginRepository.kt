package com.example.faunaonboarding.login

import com.example.faunaonboarding.createAccount.AccountDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
open class LoginRepository @Inject constructor(
    private val accountDataSource: AccountDataSource,
    override val coroutineContext: CoroutineContext = Dispatchers.IO,
) : CoroutineScope {
    private val scope = CoroutineScope(coroutineContext)

    private val requestCodeFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val getRequestCodeFlow: Flow<Result<Boolean>> = requestCodeFlow

    open suspend fun requestCode(phone: String) = scope.async {
        accountDataSource.requestLoginCode(phone).collect {
            requestCodeFlow.emit(it)
        }
    }

    private val accessCodeFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val accessCode: Flow<Result<Boolean>> = accessCodeFlow

    open suspend fun login(phone: String, accessToken: String) = scope.async {
        accountDataSource.login(phone, accessToken).collect {
            accessCodeFlow.emit(it)
        }
    }
}