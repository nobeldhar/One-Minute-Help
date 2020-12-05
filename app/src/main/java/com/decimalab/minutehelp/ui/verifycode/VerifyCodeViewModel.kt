package com.decimalab.minutehelp.ui.verifycode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import javax.inject.Inject

class
VerifyCodeViewModel
@Inject constructor(
        val authRepository: AuthRepository,
        val prefsHelper: SharedPrefsHelper
) : ViewModel() {

    var code: String = ""
    private val _verifyCode = MutableLiveData<AuthRequest>()
    private val _resendCode = MutableLiveData<String>()
    var _errorUi = MutableLiveData<String>()

    var getVerifyResult = Transformations.switchMap(_verifyCode) {
        authRepository.verifyOTPCode(it)
    }
    var getResendOTPResult =  Transformations.switchMap(_resendCode) {
        Log.d(TAG, "resendOTP Called")
        authRepository.resendOTPCode()
    }

    fun onVerifyClicked(){
        if (!code.isBlank()){
            _verifyCode.value = AuthRequest(code = code)
        }
    }

    fun onVerifyClicked(code: String?){
        if (!code?.isBlank()!!){
            Log.d(Companion.TAG, "onVerifyClicked: $code")
            _verifyCode.value = AuthRequest(code = code)
        }
    }

    fun onResendOTPClicked(){
        Log.d(TAG, "onResendOTPClicked: ")
        _resendCode.value = "Resend"
    }

    companion object {
        private const val TAG = "VerifyCodeViewModel"
    }
}