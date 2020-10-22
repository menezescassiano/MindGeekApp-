package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.cassiano.mindgeekapp.utils.TextChanged

class SecondAttemptPasswordViewModel : BaseViewModel() {

    var showError = ObservableBoolean(false)
    var password = ObservableField("")

    val onPassword = object : TextChanged {

        override fun onChanged(value: String?) {
            takeIf { value?.count() == MAX_CHARACTERS }?.run {
                onPasswordLimit.postValue(true)
            } ?: showError.set(false)
        }

        override fun afterChanged(value: String?) {
        }
    }
}
