package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class FirstAttemptPasswordViewModel : BaseViewModel() {

    companion object {
        const val MAX_TRIES = 3
    }

    val password = ObservableField("")
    var enabled = ObservableBoolean(true)
    var attemptCounter = 0
}
