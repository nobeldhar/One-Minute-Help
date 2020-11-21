package com.decimalab.minutehelp.ui.login


import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class
LoginViewModel
@Inject constructor(
        val authRepository: AuthRepository)
    : ViewModel() {

    var phone: String = ""
    var pass: String = ""


    val _loginInfo = MutableLiveData<AuthRequest>()

    var getLoginResult = Transformations.switchMap(_loginInfo) {
            authRepository.loginUser(it)
    }

    fun onLoginClicked(){
        Log.d(Companion.TAG, "onLoginClicked: $phone ")
        if(!phone.isBlank() and !pass.isBlank() and (pass!!.length >= 4)){
            _loginInfo.value = AuthRequest(phone.trim(), pass.trim())
        } else {
            setError()
        }
    }

    private fun setError() {


    }

    companion object {
        private const val TAG = "LoginViewModel"
    }


}