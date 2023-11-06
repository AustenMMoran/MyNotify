package com.am.mynotify.data.repository

import android.util.Log
import com.am.mynotify.data.database.NotificationDao
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val notificationDao: NotificationDao
) : NotificationRepository {
    override fun getAllNotifications(): Flow<List<Notification>> {
        return notificationDao.getAllNotifications()
    }

    override suspend fun upsertNotification(notification: Notification) {
        Log.d("lolipop", "upsertNotifications: $notification")
        notificationDao.insertNotification(notification)
    }

    override suspend fun deleteNotification(notificationId: Int) {
        Log.d("lolipop", "deleteNotification: $notificationId")
        notificationDao.deleteNotification(notificationId)
    }
}