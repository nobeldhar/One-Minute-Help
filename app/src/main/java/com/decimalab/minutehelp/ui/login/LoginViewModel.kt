package com.decimalab.minutehelp.ui.login


import android.util.Log
import androidx.lifecycle.*
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.utils.Validator

import javax.inject.Inject

class
LoginViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var phone: String = ""
    var pass: String = ""


    private val _loginInfo = MutableLiveData<AuthRequest>()
    var _errorUiLogin = MutableLiveData<String>()

    var getLoginResult = Transformations.switchMap(_loginInfo) {
        authRepository.loginUser(it)
    }

    fun onLoginClicked() {
        Log.d(TAG, "onLoginClicked: $phone ")
        if (phone.isBlank()) {
            _errorUiLogin.value = "Empty Phone Number!"
        } else if (pass.isBlank()) {
            _errorUiLogin.value = "Empty Password!"

        } else if ((pass.length < 4)) {

            _errorUiLogin.value = "Minimum Password Length is 4 ! "


        } else if (!Validator.validatePhone(phone)) {
            _errorUiLogin.value = "Not a Valid Phone Number! "
        } else {
            _loginInfo.value = AuthRequest(phone.trim(), pass.trim())
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }


}