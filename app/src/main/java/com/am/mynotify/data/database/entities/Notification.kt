package com.am.mynotify.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.am.mynotify.util.Constants.NOTIFICATION_TABLE

@Entity(tableName = NOTIFICATION_TABLE)
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var title: String = "",
    var message: String = "",
    var isOnOrOff: Boolean = false
)
