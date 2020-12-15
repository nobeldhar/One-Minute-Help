package com.decimalab.minutehelp.ui.profile.settings.basic

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.repository.SettingsRepository
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import javax.inject.Inject

class
BasicViewModel
@Inject constructor(
        val settingsRepository: SettingsRepository,
        val prefsHelper: SharedPrefsHelper
) : ViewModel() {
    var name: String = prefsHelper.getUser()?.name.toString()
    var email: String = prefsHelper.getUser()?.email.toString()
    var phone: String? = null

    val ui = MutableLiveData<String>()

    fun onUpdateClicked(){
        ui.value = "Clicked"
    }

    fun update(): LiveData<Resource<AuthResponse>> {
        val settingsRequest = SettingsRequest(name = name, email = email)
        return settingsRepository.updateBasicInfo(settingsRequest)
    }
}