package com.example.cardmanager.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class RetrofitNetwork {
    object ServiceBuilder {
        private val client = OkHttpClient.Builder().build()

        private val BASE_URL: String =
            "https://9eaa-2804-d4b-7971-700-f933-19a5-ab6-2a87.sa.ngrok.io/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        fun <T> buildService(service: Class<T>): T {
            return retrofit.create(service)
        }
    }
}