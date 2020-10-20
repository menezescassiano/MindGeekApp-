package com.cassiano.mindgeekapp.password.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.*
import com.cassiano.mindgeekapp.internal.Constants
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_PASSWORD
import com.cassiano.mindgeekapp.internal.Router
import com.cassiano.mindgeekapp.password.view.viewmodel.SecondAttemptPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondAttemptPasswordActivity : AppCompatActivity() {

    val viewModel: SecondAttemptPasswordViewModel by viewModel()
    private val router by lazy { Router(this) }
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val _password by lazy { intent.extras?.get("password") as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_second_attempt_password).apply {
            setVariable(BR.viewModel, viewModel)
            //setVariable(BR.onClick, View.OnClickListener { onClick() })
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            observe(onPasswordLimit) {
                viewModel.password.get()?.let {
                    when (it) {
                        _password -> {
                            savePrefs(SHARED_PREF_PASSWORD, _password, "foi")
                            router.goToSettings(true)
                        }
                        else -> viewModel.showError.set(true)
                    }
                }
            }
        }
    }

    private fun savePrefs(id: String, password: String, message: String) {
        sharedPreferences.run {
            savePrefs(id, password)
            savePrefs(SHARED_PREF, true)
            /*with(sharedPreferences.edit()) {
                putString(id, password)
                commit()
            }*/

        }
        showToast(message)
    }


}