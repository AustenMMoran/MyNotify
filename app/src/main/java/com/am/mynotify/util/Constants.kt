package com.am.mynotify.util

import android.app.NotificationManager

object Constants {
    //Room Tables
    const val NOTIFICATION_TABLE = "notification_table"

    //Notification Channel
    const val CHANNEL_ID = "my_notify_channel"
    const val CHANNEL_NAME = "MyNotify"
    const val CHANNEL_DESCRIPTION = "For notifications from MyNotify"
    const val CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
}