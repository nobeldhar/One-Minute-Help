package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.requests.CreatePostRequest
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileService {

    @GET("auth/get-district-list")
    suspend fun getDistricts()
            : Response<DistrictResponse>

    @GET("auth/get-thana-list/{id}")
    suspend fun getThanas(@Path("id") i: Int)
            : Response<UpaZillaResponse>

    @GET("auth/get-city-list/{id}")
    suspend fun getCities(@Path("id") i: Int)
            : Response<CityResponse>

    @GET("auth/get-blood-list")
    suspend fun getBloodList()
            : Response<BloodResponse>

    @POST("auth/post/create")
    suspend fun createPost(@Body createPostRequest: CreatePostRequest)
            : Response<AuthResponse>

    @GET("auth/user")
    suspend fun getTimeLinePosts()
            : Response<TimeLineResponse>
}