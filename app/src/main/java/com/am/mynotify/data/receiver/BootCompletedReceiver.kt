package com.am.mynotify.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.am.mynotify.domain.notification.MyNotificationManager
import com.am.mynotify.domain.repository.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class BootCompletedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository : NotificationRepository

    @Inject
    lateinit var myNotificationManager: MyNotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("lolipop", "onReceive: ${intent?.action}")

        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {

            CoroutineScope(Dispatchers.IO + SupervisorJob()).launch{
                repository.getAllNotifications().collect{
                    for(notification in it){
                        if (notification.isOnOrOff){
                            myNotificationManager.aHandleOnNotificationChange(notification, true)
                        }
                    }
                }
            }
        }
    }
}