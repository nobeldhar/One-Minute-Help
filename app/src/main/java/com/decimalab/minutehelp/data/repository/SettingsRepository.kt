package com.decimalab.minutehelp.data.repository

import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.sources.SettingsRemoteDataSource
import com.decimalab.minutehelp.utils.performAuthOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
        private val remoteDataSource: SettingsRemoteDataSource
) {
    fun updateOtherInfo(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updateOtherInfo(settingsRequest) }
    )

    fun updateBasicInfo(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updateBasicInfo(settingsRequest) }
    )

    fun getBloodList() = performAuthOperation(
            networkCall = { remoteDataSource.getBloodList() }
    )

    fun getDistricts() = performAuthOperation(
            networkCall = { remoteDataSource.getDistricts() }
    )

    fun getThanas(i: Int) = performAuthOperation(
            networkCall = { remoteDataSource.getThanas(i) }
    )

    fun getCity(i: Int) = performAuthOperation(
            networkCall = { remoteDataSource.getCities(i) }
    )

    fun updateAddress(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updateAddress(settingsRequest) }
    )

    fun updatePassword(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updatePassword(settingsRequest) }
    )
}