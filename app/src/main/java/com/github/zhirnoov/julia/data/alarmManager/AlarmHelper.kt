package com.github.zhirnoov.julia.data.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class AlarmHelper {

    fun setAlarm(context: Context, day : Int, minute : Int, hour : Int) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiver = Intent(context, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_YEAR, day)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        Log.d("JuliaTest", "Установил оповещение ${Date()}")
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(
                calendar.timeInMillis,
                pendingIntent
            ), pendingIntent
        )
    }
}