package com.decimalab.minutehelp.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.repository.DashboardRepository
import javax.inject.Inject

class
DashboardViewModel @Inject constructor(
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