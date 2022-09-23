package com.example.cardmanager.utils

import android.content.SharedPreferences
import android.util.Log
import com.example.cardmanager.network.CardInfo
import com.google.gson.annotations.SerializedName

class SessionManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val USER_AVATAR = "USER_AVATAR"
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
        const val USER_TOKEN = "USER_TOKEN"
        const val LAST_CARD_USED = "LAST_CARD_USED"

        const val CARD_ID = "CARD_ID"
        const val CARD_FLAG = "CARD_FLAG"
        const val CARD_NUMBER = "CARD_NUMBER"
        const val CARD_EXPDATE = "CARD_EXPDATE"
        const val CARD_LIMITVALUE = "CARD_LIMITVALUE"
        const val CARD_AVAILABLEVALUE = "CARD_AVAILABLEVALUE"
    }

    fun saveLoginOnSharedPreferences(
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

    fun saveCardOnSharedPreferences(
        id_key: String,
        id: Int,
        flag_key: String,
        flag: Int,
        number_key: String,
        number: String,
        expDate_key: String,
        expDate: String,
        limitValue_key: String,
        limitValue: Float,
        availableValue_key: String,
        availableValue: Float,
    ) {
        sharedPreferences.edit()
            .putInt(CARD_ID+id_key, id)
            .putInt(CARD_FLAG+flag_key, flag)
            .putString(CARD_NUMBER+number_key, number)
            .putString(CARD_EXPDATE+expDate_key, expDate)
            .putFloat(CARD_LIMITVALUE+limitValue_key, limitValue)
            .putFloat(CARD_AVAILABLEVALUE+availableValue_key, availableValue)
            .apply()
        Log.i("requestRetrofit", CARD_ID+id_key)
    }

    fun saveLastCardUsedOnSharedPreferences(
        lastCardUsed: Int
    ) {
        sharedPreferences.edit()
            .putInt(LAST_CARD_USED, lastCardUsed)
            .apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", null)
    }

    fun getCardId(key: String): Int? {
        return sharedPreferences.getInt(CARD_ID+key, 0)
    }
}