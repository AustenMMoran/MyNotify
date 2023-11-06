package com.am.mynotify.presentation.componants

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UiNotificationItem(
    notification: Notification,
    setNotification: (Boolean) -> Unit,
    test: (Notification) -> Unit
) {
    var isActive by remember { mutableStateOf(notification.isOnOrOff) }

    ListItem(
        modifier = Modifier
            .combinedClickable(
                onClick = {},
                onLongClick = {test(notification)}
            ),
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