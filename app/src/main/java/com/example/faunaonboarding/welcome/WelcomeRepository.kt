package com.example.faunaonboarding.welcome

import com.example.faunaonboarding.util.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WelcomeRepository @Inject constructor(
    private val userUtil: User
) {

//    fun seenOnBoarding(): Boolean = userUtil.getSeenOnBoard()
//    fun setSeenOnBoarding() = userUtil.setSeenOnBoarding()
//
//    fun setCompletedUserInfo() = userUtil.setCompletedUserInfo()
//    fun completedUserInfo(): Boolean = userUtil.getCompletedUserInfo()

    fun seenOnBoarding(): Boolean = false
    fun setSeenOnBoarding() = false

    fun setCompletedUserInfo() = false
    fun completedUserInfo(): Boolean = false

}