package com.am.mynotify.domain.repository

import com.am.mynotify.data.database.entities.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getAllNotifications(): Flow<List<Notification>>
    suspend fun upsertNotification(notification:Notification)

    suspend fun deleteNotification(notificationTitle: String)


}