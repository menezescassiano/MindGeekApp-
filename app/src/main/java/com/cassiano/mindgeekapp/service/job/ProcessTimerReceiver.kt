package com.cassiano.mindgeekapp.service.job

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.cassiano.mindgeekapp.R
import com.cassiano.mindgeekapp.extension.getSharedPreferences
import com.cassiano.mindgeekapp.extension.savePrefs
import com.cassiano.mindgeekapp.internal.Constants.Companion.BROADCAST_NAME
import com.cassiano.mindgeekapp.internal.Constants.Companion.SHARED_PREF_LOCKED

class ProcessTimerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            it.getSharedPreferences(it.getString(R.string.app_shared_preferences)).savePrefs(SHARED_PREF_LOCKED, false)
            it.sendBroadcast(Intent(BROADCAST_NAME))
        }
    }
}
