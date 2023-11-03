package com.am.mynotify.presentation.screens.your_notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.domain.notification.MyNotificationManager
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
    private val myNotificationManager: MyNotificationManager
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
            myNotificationManager.aHandleOnNotificationChange(
                notification,
                isActive
            )
        }

    }
}