package com.cassiano.mindgeekapp.password.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.bindingContentView
import com.cassiano.mindgeekapp.extension.getSharedPreferences
import com.cassiano.mindgeekapp.extension.observe
import com.cassiano.mindgeekapp.extension.savePrefs
import com.cassiano.mindgeekapp.internal.Constants.Companion.PASSWORD
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_PASSWORD
import com.cassiano.mindgeekapp.password.view.viewmodel.SecondAttemptPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondAttemptPasswordActivity : AppCompatActivity() {

    val viewModel: SecondAttemptPasswordViewModel by viewModel()
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val mPassword by lazy { intent.extras?.get(PASSWORD) as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_second_attempt_password).apply {
            setVariable(BR.viewModel, viewModel)
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            observe(onPasswordLimit) {
                viewModel.password.get()?.let {
                    when (it) {
                        mPassword -> {
                            savePrefs()
                            finishAffinity()
                        }
                        else -> viewModel.showError.set(true)
                    }
                }
            }
        }
    }

    private fun savePrefs() {
        sharedPreferences.run {
            savePrefs(SHARED_PREF_PASSWORD, mPassword)
            savePrefs(SHARED_PREF, true)
        }
    }
}
