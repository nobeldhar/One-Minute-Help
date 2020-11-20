package com.decimalab.minutehelp.data.remote.services

import LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun loginUser(
            @Field("phone") phone: String,
            @Field("password") password: String
    ): Response<LoginResponse>
}