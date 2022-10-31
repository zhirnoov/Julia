package com.github.zhirnoov.julia.data.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*


class AlarmHelper {

    fun setAlarm(context: Context, day: Int) {
        Log.d("JuliaTest", "AlarmHelper ${Date()}")

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiver = Intent(context, AlarmReceiver::class.java)
       // var pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, PendingIntent.FLAG_MUTABLE)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(context, 0, receiver, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_YEAR, day)
            set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR))
            set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE))
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent), pendingIntent
        )
    }
}