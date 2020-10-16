package com.cassiano.mindgeekapp.internal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.password.view.activity.FirstAttemptPasswordActivity

class Router(private val activity: AppCompatActivity) {

    private fun goToScreen(startActivity: () -> Unit) {
        activity.run {
            startActivity()
        }
    }

    fun goToPassword() {
        activity.run {
            goToScreen { startActivity(Intent(this, FirstAttemptPasswordActivity::class.java)) }
        }
    }

}