package com.decimalab.minutehelp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.daos.ProfileDao
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.repository.AuthRepository
import javax.inject.Inject

class
OneMinuteHelpViewModel
@Inject constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    private val _logout = MutableLiveData<Boolean>()
    var getLogoutResult = Transformations.switchMap(_logout) {
        authRepository.logoutUser()
    }
    fun logoutUser(){
        _logout.value = true
    }


}