package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.requests.CreatePostRequest
import com.decimalab.minutehelp.data.remote.services.DashboardService
import com.decimalab.minutehelp.data.remote.services.ProfileService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService
): BaseDataSource() {

    suspend fun getBloodList() =
        getResult { profileService.getBloodList() }

    suspend fun getDistricts()  =
        getResult { profileService.getDistricts() }

    suspend fun getThanas(i: Int)  =
        getResult { profileService.getThanas(i) }

    suspend fun getCities(i: Int)  =
        getResult { profileService.getCities(i) }

    suspend fun getTimeLinePosts()  =
            getResult { profileService.getTimeLinePosts() }

    suspend fun createPost(createPostRequest: CreatePostRequest) =
        getResult { profileService.createPost(createPostRequest) }

}