package com.decimalab.minutehelp.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.ui.login.LoginViewModel
import javax.inject.Inject

class
RegisterViewModel
@Inject constructor(
        val authRepository: AuthRepository)
    : ViewModel() {

    var name: String = ""
    var email: String = ""
    var phone: String = ""
    var pass: String = ""
    var confirm_pass: String = ""


    val _loginInfo = MutableLiveData<AuthRequest>()

    val getRegisterResult = Transformations.switchMap(_loginInfo) {
        authRepository.registerUser(it)
    }

    fun onRegisterClicked() {
        if (!name.isBlank() and
                !phone.isBlank() and
                !pass.isBlank() and
                !confirm_pass.isBlank() and
                (pass.length >= 6)) {

            val request = AuthRequest(name = name, phone = phone, email = email,
                    pass = pass, password_confirmation = confirm_pass)

            _loginInfo.value = request
        }
    }

    companion object {
        private const val TAG = "RegisterViewModel"
    }
}