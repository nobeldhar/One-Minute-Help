package com.decimalab.minutehelp.data.repository

import com.decimalab.minutehelp.data.local.daos.ProfileDao
import com.decimalab.minutehelp.data.remote.requests.CreatePostRequest
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.sources.AuthRemoteDataSource
import com.decimalab.minutehelp.data.remote.sources.ProfileRemoteDataSource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.performAuthOperation
import com.decimalab.minutehelp.utils.performGetOperation
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val prefsHelper: SharedPrefsHelper,
    private val localDataSource: ProfileDao
) {
    fun getBloodList() = performAuthOperation(
        networkCall = { remoteDataSource.getBloodList() }
    )

    fun getDistricts() = performAuthOperation(
        networkCall = { remoteDataSource.getDistricts() }
    )

    fun getThanas(i: Int) = performAuthOperation(
        networkCall = { remoteDataSource.getThanas(i) }
    )

    fun getCities(i: Int) = performAuthOperation(
        networkCall = { remoteDataSource.getCities(i) }
    )

    fun createPost(createPostRequest: CreatePostRequest) = performAuthOperation(
            networkCall = { remoteDataSource.createPost(createPostRequest) }
    )



    fun uploadProfileImage(part: MultipartBody.Part) = performAuthOperation(
    networkCall = { remoteDataSource.uploadProfileImage(part) }
    )

    fun getProfileImage() = performAuthOperation(
        networkCall = { remoteDataSource.getProfileImage() }
    )

    fun getTimeLinePosts() = performGetOperation(
        databaseQuery = {localDataSource.getTimeLinePosts()},
        networkCall = { remoteDataSource.getTimeLinePosts()},
        saveCallResult = {localDataSource.insertTimeLinePost(it.data.posts)}
    )

    fun likePost(id: Int) = performAuthOperation(
        networkCall = { remoteDataSource.likePost(id) }
    )
}