package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.BloodResponse
import com.decimalab.minutehelp.data.remote.responses.DistrictResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingsService {

    @POST("auth/update/other")
    suspend fun updateOtherInfo(@Body settingsRequest: SettingsRequest)
    : Response<AuthResponse>

    @POST("auth/update/basic")
    suspend fun updateBasicInfo(@Body settingsRequest: SettingsRequest)
            : Response<AuthResponse>

    @POST("auth/update/address")
    suspend fun updateAddress(@Body settingsRequest: SettingsRequest)
            : Response<AuthResponse>

    @POST("auth/update/password")
    suspend fun updatePassword(@Body settingsRequest: SettingsRequest)
            : Response<AuthResponse>

    @GET("auth/get-blood-list")
    suspend fun getBloodList()
            : Response<BloodResponse>

    @GET("auth/get-district-list")
    suspend fun getDistricts()
            : Response<DistrictResponse>

    @GET("auth/get-thana-list/{id}")
    suspend fun getThanas(@Path("id") i: Int)
            : Response<DistrictResponse>

    @GET("auth/get-city-list/{id}")
    suspend fun getCities(@Path("id") i: Int)
            : Response<DistrictResponse>
}