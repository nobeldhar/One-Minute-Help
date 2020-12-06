package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface DashboardService {

    @GET("auth/verify-login")
    suspend fun verifyAuthToken(): Response<AuthResponse>

}