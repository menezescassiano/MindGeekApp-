package com.cassiano.mindgeekapp.splash.view.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.bindingContentView
import com.cassiano.mindgeekapp.extension.getSharedPreferences
import com.cassiano.mindgeekapp.internal.Constants
import com.cassiano.mindgeekapp.internal.Router
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIMER = 1500L
    }

    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val router by lazy { Router(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        handleNextScreen()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_splash)
        lottieAnimation.setAnimation(R.raw.settings)
    }

    private fun handleNextScreen() {
        sharedPreferences.run {
            when {
                contains(Constants.SHARED_PREF_PASSWORD) || getBoolean(Constants.SHARED_PREF, false) ->
                    Handler().postDelayed({ router.goToPassword(true) }, TIMER)

                else -> Handler().postDelayed({ router.goToSettings(true) }, TIMER)
            }
        }
    }
}
