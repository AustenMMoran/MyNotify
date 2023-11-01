package com.am.mynotify.presentation.screens.yournotifications

import androidx.lifecycle.ViewModel
import com.am.mynotify.data.database.NotificationDatabase
import com.am.mynotify.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YourNotificationsViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

}