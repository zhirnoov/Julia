package com.github.zhirnoov.julia.utlis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.github.zhirnoov.julia.R

class NotificationHelper() {

    fun showNotification(context: Context) {
        val notification =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "channel_name"
        val channelID = "channel_id"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notification.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelID)
            .setContentTitle("title")
            .setContentText("text")
            .setSmallIcon(R.drawable.julia_icon_foreground)

        notification.notify(1, builder.build())
    }
}
