package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.cassiano.mindgeekapp.utils.TextChanged

class FirstAttemptPasswordViewModel : BaseViewModel() {

    companion object {
        const val MAX_TRIES = 3
    }

    val password = ObservableField("")
    var enabled = ObservableBoolean(true)
    var attemptCounter = 0

    val onPasswordChanged = object : TextChanged {

        override fun onChanged(value: String?) {
            takeIf { value?.count() == MAX_CHARACTERS }?.run {
                onPasswordLimit.postValue(true)
            }
        }

        override fun afterChanged(value: String?) {
        }
    }
}
