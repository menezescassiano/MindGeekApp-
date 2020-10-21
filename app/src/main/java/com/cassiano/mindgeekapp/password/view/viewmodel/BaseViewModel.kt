package com.cassiano.mindgeekapp.password.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cassiano.mindgeekapp.utils.TextChanged

open class BaseViewModel : ViewModel() {

    companion object {
        const val MAX_CHARACTERS = 4
    }

    var onPasswordLimit = MutableLiveData<Boolean>()

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
