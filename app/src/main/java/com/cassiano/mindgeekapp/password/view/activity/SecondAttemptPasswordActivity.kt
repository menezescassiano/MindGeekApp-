package com.cassiano.mindgeekapp.password.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.bindingContentView
import com.cassiano.mindgeekapp.extension.observe
import com.cassiano.mindgeekapp.extension.showToast
import com.cassiano.mindgeekapp.internal.Router
import com.cassiano.mindgeekapp.password.view.viewmodel.SecondAttemptPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondAttemptPasswordActivity : AppCompatActivity() {

    val viewModel: SecondAttemptPasswordViewModel by viewModel()
    private val router by lazy { Router(this) }

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
                showToast("ok, man 2")
            }
        }
    }

}