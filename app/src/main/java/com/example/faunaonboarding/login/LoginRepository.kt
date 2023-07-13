package com.example.faunaonboarding.login

import com.example.faunaonboarding.createAccount.AccountDataSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val accountDataSource: AccountDataSource,
//    override val coroutineContext: CoroutineContext,
) {
//    private val scope = CoroutineScope(coroutineContext)

    private val requestCodeFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val getRequestCodeFlow: Flow<Result<Boolean>> = requestCodeFlow

    fun requestCode(phone: String): Result<Boolean> {
//        accountDataSource.requestLoginCode(phone).collect {
//            requestCodeFlow.emit(it)
//        }
        return (Result.success(true))

    }

    private val accessCodeFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val getLoginFlow: Flow<Result<Boolean>> = accessCodeFlow

    suspend fun login(phone: String, accessToken: String): Result<Boolean> {
//        accountDataSource.login(phone, accessToken).collect {
//            accessCodeFlow.emit(it)
//        }
        return (Result.success(true))
    }
}