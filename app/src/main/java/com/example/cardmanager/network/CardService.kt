package com.example.cardmanager.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CardService {
    @GET("/api/card")
    fun getCards(@Header("Authorization") token: String): Call<List<CardInfo>>
}