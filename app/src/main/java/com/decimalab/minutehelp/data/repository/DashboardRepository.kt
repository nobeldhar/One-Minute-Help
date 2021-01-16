package com.decimalab.minutehelp.data.repository

import com.decimalab.minutehelp.data.remote.sources.AuthRemoteDataSource
import com.decimalab.minutehelp.data.remote.sources.DashboardRemoteDataSource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.performAuthOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
        private val remoteDataSource: DashboardRemoteDataSource
) {
    fun verifyAuthToken() = performAuthOperation(
            networkCall = { remoteDataSource.verifyAuthToken() }
    )

    fun commentOnPost(comment: String, postId: Int, p_id:Int? = null, reply_id: Int? = null) = performAuthOperation(
        networkCall = { remoteDataSource.commentOnPost(comment, postId, p_id, reply_id) }
    )

    fun getComments(postId: Int) = performAuthOperation(
        networkCall = { remoteDataSource.getComments(postId) }
    )
}