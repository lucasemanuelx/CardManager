package com.example.cardmanager.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login")
    fun getOnLogin(@Body request: UserInfo): Call<UserInfo>
}