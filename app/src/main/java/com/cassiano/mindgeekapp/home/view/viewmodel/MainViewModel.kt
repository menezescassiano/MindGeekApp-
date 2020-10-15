package com.cassiano.mindgeekapp.home.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var isChecked = ObservableBoolean(false)
}