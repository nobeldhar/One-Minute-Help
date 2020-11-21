package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun loginUser(@Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun registerUser(@Body authRequest: AuthRequest
    ): Response<AuthResponse>
}