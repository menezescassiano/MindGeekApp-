package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cassiano.mindgeekapp.utils.TextChanged

class FirstAttemptPasswordViewModel : ViewModel() {

    val password = ObservableField("")
    var onPasswordLimit = MutableLiveData<Boolean>()

    val onPasswordChanged = object : TextChanged {

        override fun onChanged(value: String?) {
            takeIf { value?.count() == 4 }?.run {
                onPasswordLimit.postValue(true)
            }
        }

        override fun afterChanged(value: String?) {
        }
    }
}