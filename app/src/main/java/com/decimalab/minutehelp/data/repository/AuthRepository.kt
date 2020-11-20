package com.decimalab.minutehelp.data.repository

import LoginResponse
import User
import com.decimalab.minutehelp.data.remote.services.AuthService
import com.decimalab.minutehelp.data.remote.sources.AuthRemoteDataSource
import com.decimalab.minutehelp.utils.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val prefsHelper: SharedPrefsHelper
) {

    fun loginUser(phone: String, password: String) = performAuthOperation(
            networkCall = { remoteDataSource.loginUser(phone, password) },
            saveAuthInfo = { prefsHelper.saveUser(it)}
    )






}
