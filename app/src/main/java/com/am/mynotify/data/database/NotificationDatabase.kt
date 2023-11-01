package com.am.mynotify.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.am.mynotify.data.database.entities.Notification

@Database(
    entities = [Notification::class],
    version = 1,
    exportSchema = false
)
abstract class NotificationDatabase: RoomDatabase(){
    abstract fun NotificationDao(): NotificationDao
}
