package com.am.mynotify.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.am.mynotify.data.database.entities.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Upsert
    suspend fun insertNotification(notification: Notification)

    @Query("SELECT * FROM notification_table")
    fun getAllNotifications(): Flow<List<Notification>>

}