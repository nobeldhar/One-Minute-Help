package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.services.DashboardService
import com.decimalab.minutehelp.data.remote.services.SettingsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRemoteDataSource @Inject constructor(
        private val settingsService: SettingsService
): BaseDataSource() {

    suspend fun updateOtherInfo(settingsRequest: SettingsRequest) =
            getResult { settingsService.updateOtherInfo(settingsRequest) }

    suspend fun updateBasicInfo(settingsRequest: SettingsRequest) =
            getResult { settingsService.updateBasicInfo(settingsRequest) }

    suspend fun updateAddress(settingsRequest: SettingsRequest) =
            getResult { settingsService.updateAddress(settingsRequest) }

    suspend fun updatePassword(settingsRequest: SettingsRequest) =
            getResult { settingsService.updatePassword(settingsRequest) }

    suspend fun getBloodList() =
            getResult { settingsService.getBloodList() }

    suspend fun getDistricts()  =
            getResult { settingsService.getDistricts() }

    suspend fun getThanas(i: Int)  =
            getResult { settingsService.getThanas(i) }

    suspend fun getCities(i: Int)  =
            getResult { settingsService.getCities(i) }

}