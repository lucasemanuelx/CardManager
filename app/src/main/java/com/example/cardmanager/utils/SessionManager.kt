package com.example.cardmanager.utils

import android.content.SharedPreferences

class SessionManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val USER_AVATAR = "USER_AVATAR"
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
        const val USER_TOKEN = "USER_TOKEN"
    }

    fun saveOnSharedPreferences(
        avatar: String,
        name: String,
        email: String,
        token: String
    ) {
        sharedPreferences.edit()
            .putString(USER_AVATAR, avatar)
            .putString(USER_NAME, name)
            .putString(USER_EMAIL, email)
            .putString(USER_TOKEN, token)
            .apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", null)
    }
}