package com.decimalab.minutehelp.data.repository

import com.decimalab.minutehelp.data.remote.sources.AuthRemoteDataSource
import com.decimalab.minutehelp.data.remote.sources.ProfileRemoteDataSource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val prefsHelper: SharedPrefsHelper
) {
}