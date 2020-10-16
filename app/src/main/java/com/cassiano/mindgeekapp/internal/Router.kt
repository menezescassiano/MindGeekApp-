package com.cassiano.mindgeekapp.internal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.home.view.activity.SettingsActivity
import com.cassiano.mindgeekapp.password.view.activity.FirstAttemptPasswordActivity
import com.cassiano.mindgeekapp.password.view.activity.SecondAttemptPasswordActivity

class Router(private val activity: AppCompatActivity) {

    private fun goToScreen(startActivity: () -> Unit) {
        activity.run {
            startActivity()
        }
    }

    fun goToSettings(hasToFinish: Boolean = false) {
        activity.run {
            goToScreen {
                Intent(this, SettingsActivity::class.java).apply {
                    startActivity(this)
                    when {
                        hasToFinish -> finish()
                    }
                }
            }
        }
    }

    fun goToPassword(hasToFinish: Boolean) {
        activity.run {
            goToScreen {
                Intent(this, FirstAttemptPasswordActivity::class.java).apply {
                    startActivity(this)
                    when {
                        hasToFinish -> finish()
                    }
                }
            }
        }
    }

    fun goToPasswordSecondAttempt() {
        activity.run {
            goToScreen { startActivity(Intent(this, SecondAttemptPasswordActivity::class.java)) }
        }
    }

}