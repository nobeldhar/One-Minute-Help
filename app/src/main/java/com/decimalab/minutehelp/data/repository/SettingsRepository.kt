package com.decimalab.minutehelp.data.repository

import com.decimalab.minutehelp.data.local.daos.AddressDao
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.sources.SettingsRemoteDataSource
import com.decimalab.minutehelp.utils.performAuthOperation
import com.decimalab.minutehelp.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
        private val remoteDataSource: SettingsRemoteDataSource,
        private val localDataSource: AddressDao
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

    fun getDistricts() = performGetOperation(
            databaseQuery = {localDataSource.getDistricts()},
            networkCall = { remoteDataSource.getDistricts() },
            saveCallResult = {localDataSource.insertDistricts(it.data)}
    )

    fun getThanas(i: Int) = performGetOperation(
            databaseQuery = {localDataSource.getThanas(i)},
            networkCall = { remoteDataSource.getThanas(i) },
            saveCallResult = {localDataSource.insertThanas(it.data)}
    )

    fun getCity(i: Int) = performGetOperation(
            databaseQuery = {localDataSource.getCites(i)},
            networkCall = { remoteDataSource.getCities(i)},
            saveCallResult = {localDataSource.insertCities(it.data)}
    )

    fun updateAddress(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updateAddress(settingsRequest) }
    )

    fun updatePassword(settingsRequest: SettingsRequest) = performAuthOperation(
            networkCall = { remoteDataSource.updatePassword(settingsRequest) }
    )
}