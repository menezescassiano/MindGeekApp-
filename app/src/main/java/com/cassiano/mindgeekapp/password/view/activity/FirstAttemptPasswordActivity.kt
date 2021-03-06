package com.cassiano.mindgeekapp.password.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.mindgeekapp.BR
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.*
import com.cassiano.mindgeekapp.internal.Constants.Companion.ATTEMPT_COUNTER
import com.cassiano.mindgeekapp.internal.Constants.Companion.BROADCAST_NAME
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_LOCKED
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_PASSWORD
import com.cassiano.mindgeekapp.internal.Router
import com.cassiano.mindgeekapp.password.view.viewmodel.FirstAttemptPasswordViewModel
import com.cassiano.mindgeekapp.password.view.viewmodel.FirstAttemptPasswordViewModel.Companion.MAX_TRIES
import com.cassiano.mindgeekapp.service.job.ProcessTimerReceiver
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstAttemptPasswordActivity : AppCompatActivity() {

    val viewModel: FirstAttemptPasswordViewModel by viewModel()
    private val router by lazy { Router(this) }
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.app_shared_preferences)) }
    private val TAG = FirstAttemptPasswordActivity::class.java.simpleName

    companion object {
        const val TIMER = 60L * 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
        handleSharedPreferences()
    }

    private fun handleSharedPreferences() {
        sharedPreferences.run {
            if (contains(SHARED_PREF_LOCKED)) {
                viewModel.enabled.set(!getBoolean(SHARED_PREF_LOCKED, false))
            }

            if (contains(SHARED_PREF_PASSWORD)) {
                registerReceiver(broadcastReceiver, IntentFilter(BROADCAST_NAME))
                setTitle(R.string.enter_passcode_title)
            }
        }
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            viewModel.enabled.set(true)
        }
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_first_attempt_password).apply {
            setVariable(BR.viewModel, viewModel)
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            observe(onPasswordLimit) {
                password.get()?.let {
                    when {
                        sharedPreferences.contains(SHARED_PREF_PASSWORD) -> {
                            when (it) {
                                sharedPreferences.getString(SHARED_PREF_PASSWORD, "") -> {
                                    savePrefCount(0)
                                    router.goToSettings(true)
                                    Log.d(TAG, "Access granted")
                                }
                                else -> {
                                    attemptCounter = sharedPreferences.getInt(ATTEMPT_COUNTER, 0)
                                    attemptCounter++
                                    when (attemptCounter) {
                                        MAX_TRIES -> {
                                            attemptCounter = 0
                                            enabled.set(false)
                                            Log.d(TAG, "Reached max tries")

                                            launchTestService()
                                        }
                                        else -> {
                                            showToast(getString(R.string.incorrect_password_message))

                                            Log.d(TAG, "Incorrect password")
                                        }
                                    }
                                    savePrefCount(attemptCounter)
                                }
                            }
                        }
                        else -> {
                            router.goToPasswordSecondAttempt(it)
                        }
                    }
                }
            }
        }
    }

    private fun savePrefCount(count: Int) {
        sharedPreferences.savePrefs(ATTEMPT_COUNTER, count)
    }

    private fun launchTestService() {
        sharedPreferences.savePrefs(SHARED_PREF_LOCKED, true)
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent: PendingIntent = Intent(this, ProcessTimerReceiver::class.java).let {
            PendingIntent.getBroadcast(this, 0, it, 0)
        }

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + TIMER, alarmIntent)

        startService(Intent(this, ProcessTimerReceiver::class.java))
        Log.d(TAG, "Service has started")
    }
}
