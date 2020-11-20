package com.decimalab.minutehelp.ui.login


import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
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


    val _loginInfo = MutableLiveData<List<String>>()

    val getLoginResult = Transformations.switchMap(_loginInfo) {
        if (it.isNotEmpty()) {
            Log.d(TAG, "getLoginResult: ")
            authRepository.loginUser(it[0], it[1])
        } else {
            MutableLiveData()
        }
    }


    fun onLoginClicked(){
        Log.d(Companion.TAG, "onLoginClicked: $phone ")
        if(!phone.isNullOrBlank() and !pass.isNullOrBlank() and (pass!!.length >= 4)){
            _loginInfo.value = listOf(phone, pass)
        }

    }

    companion object {
        private const val TAG = "LoginViewModel"
    }


}