package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.services.DashboardService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRemoteDataSource @Inject constructor(
        private val dashboardService: DashboardService
): BaseDataSource() {

    suspend fun verifyAuthToken()
            = getResult { dashboardService.verifyAuthToken() }
}