package com.example.cardmanager.model

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cardmanager.network.*
import com.example.cardmanager.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(
    sharedPreferences: SharedPreferences
) : ViewModel() {

    //TODO make it a singleton
    val sessionManager = SessionManager(sharedPreferences)

    //TODO remove params
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

    fun onLogin(request: UserInfo) {
        if (isLoginDataValid()) {
            onLoginService(request) {}
            _shouldNavigateHome.postValue(true)
        }
    }

    //todo tratar caso de response: response.body() = null
    private fun onLoginService(request: UserInfo, onResult: (UserInfo?) -> Unit) {
        val retrofit = RetrofitNetwork.ServiceBuilder.buildService(LoginService::class.java)

        retrofit.getOnLogin(request).enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.i("requestRetrofit", "${t.message}")
                    onResult(null)
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val userLogin = response.body()
                    Log.d("requestToken", "${userLogin?.token}")
                    sessionManager.saveLoginOnSharedPreferences(
                        userLogin!!.avatar!!,
                        userLogin.name!!,
                        userLogin.email!!,
                        userLogin.token!!
                    )
                    Log.d("requestSPToken", "${sessionManager.getToken()}")
                    //TODO implement in MyCards View Model
                    onCardService()
                }
            }
        )
    }

    //TODO move to MyCardsFragment
    private fun onCardService() {
        val retrofit = RetrofitNetwork.ServiceBuilder.buildService(CardService::class.java)
        val token = "Bearer ${sessionManager.getToken()}"
        val callback = retrofit.getCards(token)
        Log.d("requestSPToken2", "${sessionManager.getToken()}")

        callback.enqueue(object : Callback<List<CardInfo>> {
            override fun onFailure(call: Call<List<CardInfo>>, t: Throwable) {
                Log.i("requestRetrofit", "${t.message}")
            }

            override fun onResponse(
                call: Call<List<CardInfo>>,
                response: Response<List<CardInfo>>
            ) {
                val cards = response.body()
                var iterator = 0
                cards?.forEach {
                    sessionManager.saveCardOnSharedPreferences(
                        "${iterator}", it.id!!,
                        "${iterator}", it.flag!!,
                        "${iterator}", it.number!!,
                        "${iterator}", it.expDate!!,
                        "${iterator}", it.limitValue!!,
                        "${iterator}", it.availableValue!!,
                    )
                    Log.d("requestRetrofit", "${sessionManager.getCardId("$iterator")}")
                    iterator += 1
                }
            }
        }
        )
    }
}