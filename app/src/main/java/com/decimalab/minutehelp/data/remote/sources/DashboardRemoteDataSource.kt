package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.services.DashboardService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRemoteDataSource @Inject constructor(
    private val dashboardService: DashboardService
) : BaseDataSource() {

    suspend fun verifyAuthToken() = getResult { dashboardService.verifyAuthToken() }

    suspend fun commentOnPost(comment: String, postId: Int, p_id:Int? = null, reply_id: Int? = null) =
        getResult { dashboardService.commentOnPost(comment, postId, p_id, reply_id) }

    suspend fun getComments(postId: Int) =
        getResult { dashboardService.getComments(postId) }
}