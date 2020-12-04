package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import retrofit2.Response

import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth/login")
    suspend fun loginUser(@Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun registerUser(@Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @POST("auth/verify")
    suspend fun verifyCode(@Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @FormUrlEncoded
    @GET("auth/resend-code/{id}")
    suspend fun resendCode(@Path("id") id: Int
    ): Response<AuthResponse>
}