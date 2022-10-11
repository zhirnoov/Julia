package com.github.zhirnoov.julia.utlis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.presentation.MainActivity
import com.github.zhirnoov.julia.presentation.bottom_nav.BottomNavigationItem

class NotificationHelper {

    fun showNotification(context: Context) {
        val notification =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "channel_name"
        val channelID = "channel_id"
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            TaskStackBuilder.create(context)
                .addParentStack(MainActivity::class.java)
                .addNextIntent(intent)
                .getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID, channelName, NotificationManager.IMPORTANCE_HIGH
            )
            notification.createNotificationChannel(channel)
        }

        val builder =
            NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.julia_small_icon)
                .setContentTitle("Вы начинаете забывать...")
                .setContentText("Пришло время повторить карты!")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, true)
                .addAction(R.drawable.icon_repeat, "Повторить", pendingIntent)
                .setAutoCancel(true)

        notification.notify(1, builder.build())
    }
}
