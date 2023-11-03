package com.am.mynotify.presentation.screens.your_notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.mynotify.R
import com.am.mynotify.presentation.componants.UiNotificationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourNotificationScreen(
    yourNotificationsViewModel: YourNotificationsViewModel,
    navigateToCreate : () -> Unit
){

    //Collect flow as state to ensure updates will trigger recomposition
    val uiNotificationList = yourNotificationsViewModel.notificationList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        fontSize = 32.sp,
                        text = "Your Notifications"
                    )

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.background),
                modifier = Modifier.padding(12.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToCreate()
                },
                shape = RoundedCornerShape(16.dp),
                elevation = FloatingActionButtonDefaults.elevation(),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_36dp),
                        contentDescription = "Create icon"
                    )
                },
                modifier = Modifier
            )
        },

        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    items(
                        uiNotificationList.value
                    ) { item ->
                        UiNotificationItem(
                            notification = item,
                            setNotification = {isActive ->
                                yourNotificationsViewModel.aHandleOnNotificationChange(
                                    item,
                                    isActive
                                )
                            }
                        )
                    }
                }
            }
        }
    )
}