package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.services.DashboardService
import com.decimalab.minutehelp.data.remote.services.ProfileService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService
): BaseDataSource() {

}