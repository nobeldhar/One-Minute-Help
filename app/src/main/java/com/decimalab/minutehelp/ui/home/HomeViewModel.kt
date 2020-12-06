package com.decimalab.minutehelp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
HomeViewModel @Inject constructor(
        private val dashboardRepository: DashboardRepository
) : ViewModel() {

    val _verifyAuthToken = MutableLiveData<Boolean>()

    var getVerifyTokenResult = Transformations.switchMap(_verifyAuthToken) {
        Log.d(Companion.TAG, "verifyAuthToken: called ")
        dashboardRepository.verifyAuthToken()
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }


}