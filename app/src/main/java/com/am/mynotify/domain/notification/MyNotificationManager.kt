package com.am.mynotify.domain.notification

import com.am.mynotify.data.database.entities.Notification

interface MyNotificationManager {
    suspend fun aHandleOnNotificationChange(notification: Notification, isActive: Boolean)
}