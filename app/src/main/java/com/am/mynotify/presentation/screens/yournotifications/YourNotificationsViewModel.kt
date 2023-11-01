package com.am.mynotify.presentation.screens.yournotifications

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class YourNotificationsViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
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

    fun addOne(){
        val notification = Notification(
            0,
            "123",
            "123"
        )

        viewModelScope.launch {
            notificationRepository.insertNotifications(notification = notification)

        }
    }
}