package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    companion object {
        const val MAX_CHARACTERS = 4
    }

    var onPasswordLimit = MutableLiveData<Boolean>()
}
