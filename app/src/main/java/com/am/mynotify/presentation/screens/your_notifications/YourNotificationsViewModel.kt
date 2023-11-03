package com.am.mynotify.presentation.screens.your_notifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.mynotify.R
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourNotificationsViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val notificationManager: NotificationManager,
    private val notificationBuilder: NotificationCompat.Builder
) : ViewModel() {

    init {
        getNotificationList()
    }

    private val _notificationList = MutableStateFlow(emptyList<Notification>())
    var notificationList = _notificationList.asStateFlow()

    private fun getNotificationList(){
        viewModelScope.launch(Dispatchers.IO){
            notificationRepository.getAllNotifications().collect{ updateList ->
                _notificationList.update {
                    updateList
                }
            }
        }
    }

    fun aHandleOnNotificationChange(
        notification: Notification,
        isActive: Boolean
    ){
        Log.d("lolipop", "launchNotification: $notification.title")

        viewModelScope.launch(Dispatchers.IO){
            notification.isOnOrOff = isActive
            notificationRepository.upsertNotification(notification)

            if(isActive){
                notificationBuilder
                    .setSmallIcon(R.drawable.ic_bell_24)
                    .setContentTitle(notification.title)
                    .setContentText(notification.message)


                notificationManager.notify(notification.id, notificationBuilder.build())
            } else {
                notificationManager.cancel(notification.id)
            }

            Log.d("lolipop", "launchNotification: ${notificationManager.activeNotifications.size}")
        }
    }
}