package com.am.mynotify.presentation.componants

import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.am.mynotify.data.database.entities.Notification

@Composable
fun UiNotificationItem(
    notification: Notification,
    setNotification: (Boolean) -> Unit
) {
    var isActive by remember { mutableStateOf(notification.isOnOrOff) }

    ListItem(
        modifier = Modifier,
        headlineContent = { Text(notification.title) },
        supportingContent = { Text(notification.message) },
        trailingContent = {
            Switch(
                checked = isActive,
                onCheckedChange = {
                    isActive = it
                    setNotification(isActive)
                }
            )
        }
    )
}