package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun loginUser(
            @Field("phone") phone: String,
            @Field("password") password: String
    ): Response<AuthResponse>
}