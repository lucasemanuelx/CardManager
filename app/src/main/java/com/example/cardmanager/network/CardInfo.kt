package com.example.cardmanager.network

import com.google.gson.annotations.SerializedName

data class CardInfo(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("flag")
    var flag: Int?,

    @SerializedName("number")
    var number: String?,

    @SerializedName("expDate")
    var expDate: String?,

    @SerializedName("limitValue")
    var limitValue: Float?,

    @SerializedName("availableValue")
    var availableValue: Float?,
)
