package com.decimalab.minutehelp.ui.login

import LoginResponse
import User
import androidx.lifecycle.*
import com.decimalab.minutehelp.data.repository.AuthRepository
import com.decimalab.minutehelp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class
LoginViewModel
@Inject constructor(
        val authRepository: AuthRepository)
    : ViewModel() {


    val _loginInfo = MutableLiveData<List<String>>()

    val getProfileResult = Transformations.switchMap(_loginInfo) {
        if (it.isNotEmpty()) {
            authRepository.loginUser(it[0], it[1])
        } else {
            MutableLiveData()
        }
    }





}