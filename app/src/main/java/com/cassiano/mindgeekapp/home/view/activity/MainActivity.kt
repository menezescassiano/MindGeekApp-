package com.cassiano.mindgeekapp.home.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.databinding.ActivityMainBinding
import com.cassiano.mindgeekapp.extension.bindingContentView
import com.cassiano.mindgeekapp.extension.showToast
import com.cassiano.mindgeekapp.home.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.cassiano.mindgeekapp.extension.getSharedPreferences
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF
import com.cassiano.mindgeekapp.internal.Router

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val router by lazy { Router(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        updateUI()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_main).apply {
            setVariable(BR.viewModel, viewModel)
            setVariable(BR.onClick, View.OnClickListener { onClick() })
        } as ActivityMainBinding
    }

    private fun updateUI() {
        viewModel.isChecked.set(sharedPreferences.getBoolean(SHARED_PREF, false))
    }

    private fun onClick() {
        savePrefs(SHARED_PREF, !viewModel.isChecked.get(), "ok")
    }

    private fun savePrefs(id: String, save: Boolean, message: String) {
        with(sharedPreferences.edit()) {
            putBoolean(id, save)
            commit()
        }

        showToast(message)
    }
}