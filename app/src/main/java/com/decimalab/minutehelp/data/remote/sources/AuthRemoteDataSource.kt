package com.decimalab.minutehelp.data.remote.sources

import com.decimalab.minutehelp.data.remote.services.AuthService
import com.decimalab.minutehelp.data.remote.sources.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
): BaseDataSource() {
    suspend fun loginUser(phone: String, password: String)
            = getResult { authService.loginUser(
            phone, password) }
}