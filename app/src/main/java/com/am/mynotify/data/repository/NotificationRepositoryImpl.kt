package com.am.mynotify.data.repository

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

    override suspend fun insertNotifications(notification: Notification) {
        notificationDao.insertNotification()
    }
}