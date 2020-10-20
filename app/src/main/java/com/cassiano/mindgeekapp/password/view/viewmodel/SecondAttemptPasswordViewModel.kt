package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.cassiano.mindgeekapp.utils.TextChanged

class SecondAttemptPasswordViewModel : BaseViewModel() {

    var showError = ObservableBoolean(false)
    var password = ObservableField("")

    val onPassword = object : TextChanged {

        override fun onChanged(value: String?) {
            takeIf { value?.count() == 4 }?.run {
                onPasswordLimit.postValue(true)
            } ?: showError.set(false)
        }

        override fun afterChanged(value: String?) {
        }
    }
}