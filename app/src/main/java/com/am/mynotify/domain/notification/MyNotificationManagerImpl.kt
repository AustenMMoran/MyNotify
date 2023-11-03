package com.am.mynotify.domain.notification

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.am.mynotify.R
import com.am.mynotify.data.database.entities.Notification
class MyNotificationManagerImpl(
    private val notificationManager: NotificationManager,
    private val notificationBuilder: NotificationCompat.Builder
) : MyNotificationManager {
    override suspend fun aHandleOnNotificationChange(
        notification: Notification,
        isActive: Boolean
    ){
        if(isActive){
            notificationBuilder
                .setSmallIcon(R.drawable.ic_bell_24)
                .setContentTitle(notification.title)
                .setContentText(notification.message)

            notificationManager.notify(notification.id, notificationBuilder.build())
        } else {
            notificationManager.cancel(notification.id)
        }
    }
}