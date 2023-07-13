package com.example.faunaonboarding.createAccount

import com.example.faunaonboarding.util.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDataSource @Inject constructor(
//    private val apolloClient: ApolloClient,
    private val user: User
) {

//    fun requestLoginCode(phone: String): Flow<Result<Boolean>> {
//        return apolloClient.mutate(AccessInvitationResendMutation(phone)).toFlow()
//            .onEach {
//                UIState.Loading
//            }
//            .map {
//                it.toResult {
//                    it.accessInvitationResend
//                }
//            }
//            .catch {
//                Result.Error(it.toString())
//            }
//    }

    fun requestLoginCode(phone: String): Boolean {
        // TODO mock
        return true
    }

//    fun login(phone: String, accessCode: String): Flow<Result<Boolean>> {
//        return apolloClient.mutate(LoginMutation(phone, accessCode)).toFlow()
//            .onEach {
//                UIState.Loading
//            }
//            .map {
//                it.toResult { data ->
//                    //TODO Should we do this here?
//                    user.setToken(data.login.token)
//                    true
//                }
//            }
//            .catch {
//                Result.Error(this.toString())
//            }
//    }

    fun login(phone: String, accessCode: String): Boolean {
        // TODO mock
        return true
    }
}