package com.example.cardmanager.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAccountViewModel : ViewModel() {

    private var _shouldEnableCreateAccountButton = MutableLiveData<Boolean>(false)
    val shouldEnableCreateAccountButton: LiveData<Boolean> get() = _shouldEnableCreateAccountButton

    val createAccountName = MutableLiveData<String>("")
    val createAccountEmail = MutableLiveData<String>("")
    val createAccountCellPhone = MutableLiveData<String>("")
    val createAccountCreditCardNumber = MutableLiveData<String>("")
    val createAccountTermsOfUse = MutableLiveData<Boolean>(false)

    //TODO ENABLE BUTTON DEPENDING ON IF ALL BOXES ARE FILLED, NOT IF THE BUTTON WAS CLICKED
    //TODO SEARCH OBSERVER
    fun enableButton() {
        if (nameIsValid() &&
            emailIsValid() &&
            phoneNumberIsValid() &&
            creditCardNumberIsValid()
        ) {
            _shouldEnableCreateAccountButton.postValue(shouldEnableCreateAccountButton.value!!.not())
        }
    }

    private fun nameIsValid(): Boolean {
        if (createAccountName.value!!.length >= 5) return true
        return false
    }

    private fun emailIsValid(): Boolean {
        if (createAccountEmail.value!!.contains("@") &&
            createAccountEmail.value!!.endsWith(".com")
        ) return true
        return false
    }

    private fun phoneNumberIsValid(): Boolean {
        if (createAccountCellPhone.value!!.length == 15) return true
        return false
    }

    private fun creditCardNumberIsValid(): Boolean {
        if (createAccountCreditCardNumber.value!!.length == 19) return true
        return false
    }
}