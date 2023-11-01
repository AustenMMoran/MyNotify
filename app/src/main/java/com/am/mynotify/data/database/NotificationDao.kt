package com.am.mynotify.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.am.mynotify.data.database.entities.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification()

    @Query("SELECT * FROM notification_table")
    fun getAllNotifications(): Flow<List<Notification>>

}