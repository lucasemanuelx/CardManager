package com.example.cardmanager.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var loginEmail = MutableLiveData<String>("iosbootcamp2@ciandt.com")
    var loginPassword = MutableLiveData<String>("strongpwd")

    private val _loginIsValid = MutableLiveData<Boolean>()
    val loginIsValid: LiveData<Boolean> get() = _loginIsValid

    private val _shouldNavigateHome = MutableLiveData<Boolean>()
    val shouldNavigateHome: LiveData<Boolean> get() = _shouldNavigateHome

    private fun isLoginDataValid(): Boolean {
        if ((loginEmail.value!!.contains("@") &&
                    loginEmail.value!!.endsWith(".com")) &&
            loginPassword.value!!.length > 4
        ) {
            _loginIsValid.value = true
            return true
        }
        _loginIsValid.value = false
        return false
    }
}