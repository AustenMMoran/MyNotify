package com.am.mynotify.presentation.create_notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNotificationViewModel @Inject constructor(
    private val notificationRepo : NotificationRepository
) : ViewModel() {

    fun createNotification(title: String, message: String){
        val newNotification = Notification(
            title = title,
            message = message
        )

        viewModelScope.launch(Dispatchers.IO){
            notificationRepo.insertNotifications(newNotification)
        }
    }

}