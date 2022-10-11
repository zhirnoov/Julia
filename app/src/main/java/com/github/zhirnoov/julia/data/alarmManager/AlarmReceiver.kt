package com.github.zhirnoov.julia.data.alarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("JuliaTest", "Cработало оповещение ${Date()}")
    }

}
