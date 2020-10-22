package com.cassiano.mindgeekapp.home.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.bindingContentView
import com.cassiano.mindgeekapp.extension.clearPrefs
import com.cassiano.mindgeekapp.extension.getSharedPreferences
import com.cassiano.mindgeekapp.extension.savePrefs
import com.cassiano.mindgeekapp.home.view.viewmodel.SettingsViewModel
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_PASSWORD
import com.cassiano.mindgeekapp.internal.Router
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {

    val viewModel: SettingsViewModel by viewModel()
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val router by lazy { Router(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        updateUI()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_settings).apply {
            setVariable(BR.viewModel, viewModel)
            setVariable(BR.onClick, View.OnClickListener { onClick() })
        }
    }

    private fun updateUI() {
        viewModel.isChecked.set(sharedPreferences.getBoolean(SHARED_PREF, false))
    }

    private fun onClick() {
        viewModel.run {
            sharedPreferences.savePrefs(SHARED_PREF, !isChecked.get())
            when {
                !isChecked.get() -> clearPasswPrefs()
                else -> router.goToPassword()
            }
        }
    }

    private fun clearPasswPrefs() {
        sharedPreferences.run {
            clearPrefs(SHARED_PREF_PASSWORD)
            savePrefs(SHARED_PREF, false)
        }
    }
}
