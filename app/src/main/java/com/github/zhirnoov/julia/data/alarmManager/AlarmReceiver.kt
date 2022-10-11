package com.github.zhirnoov.julia.data.alarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.github.zhirnoov.julia.utlis.NotificationHelper
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("JuliaTest", "Cработало оповещение ${Date()}")
        NotificationHelper().showNotification(context)
    }

}
