package com.nminin.interviewtimesync.ui.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

internal class TimeTicker(
    private var context: Context?,
    onTimeTick: () -> Unit,
) {
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.getAction()?.compareTo(Intent.ACTION_TIME_TICK) == 0) {
                onTimeTick.invoke()
            }
        }
    }

    init {
        context?.registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    fun stop() {
        context?.unregisterReceiver(broadcastReceiver)
        context = null
    }
}