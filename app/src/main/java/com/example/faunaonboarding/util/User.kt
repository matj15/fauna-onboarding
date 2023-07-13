package com.example.faunaonboarding.util

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class User @Inject constructor() {

    private val onBoarding = "ON_BOARDING"
    private val userInfo = "USER_INFO"
    private val keyToken = "TOKEN"
    private var token : String? = null

//    private val encryptedSharedPreferencesFileName = BuildConfig.APPLICATION_ID + "fauna"
//
//    private val spec = KeyGenParameterSpec.Builder(
//        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
//        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//    )
//        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
//        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//        .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
//        .build()
//
//    private fun preferences(): SharedPreferences {
//        val masterKey = MasterKey.Builder(context)
//            .setKeyGenParameterSpec(spec)
//            .build()
//
//        return try {
//            EncryptedSharedPreferences.create(
//                context,
//                encryptedSharedPreferencesFileName,
//                masterKey,
//                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            )
//        } catch (exception: Exception) {
//            recreateEncryptedPrefs()
//        }
//    }
//
//    private fun recreateEncryptedPrefs(): SharedPreferences {
//        // Clear the data, since the data is either tampered, broken or Tink has created a new KeySet and we cannot recover
//        context.getSharedPreferences(encryptedSharedPreferencesFileName, Context.MODE_PRIVATE)
//            .edit()
//            .clear()
//            .commit()
//        return preferences()
//    }

//    fun setSeenOnBoarding() {
//        preferences().edit().apply {
//            putBoolean(onBoarding, true)
//            apply()
//        }
//    }
//
//    fun getSeenOnBoard() : Boolean {
//        return preferences().getBoolean(onBoarding, false)
//    }
//
//    fun setCompletedUserInfo() {
//        preferences().edit().apply {
//            putBoolean(userInfo, true)
//            apply()
//        }
//    }
//
//    fun getCompletedUserInfo() : Boolean {
//        return preferences().getBoolean(userInfo, false)
//    }
//
//    fun getToken(): String? {
//        if(token != null) {
//            token
//        }
//        token = preferences().getString(keyToken, null)
//        return token
//    }
//
//    fun setToken(token: String) {
//        this.token = token
//        preferences().edit().apply {
//            putString(keyToken, token)
//            apply()
//        }
//    }
//
//    fun removeToken() {
//        token = null
//        preferences().edit().apply {
//            remove(keyToken)
//            apply()
//        }
//    }
}