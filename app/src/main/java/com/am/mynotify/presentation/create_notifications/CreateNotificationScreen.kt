package com.am.mynotify.presentation.create_notifications

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.am.mynotify.data.database.entities.Notification
import com.am.mynotify.presentation.create_notifications.componants.GetMessage
import com.am.mynotify.presentation.create_notifications.componants.GetTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNotificationScreen(
    createNotificationViewModel : CreateNotificationViewModel,
    navigateBack: () -> Unit
) {
    val titleCharacterLimit = 25
    val messageCharacterLimit = 240

    var titleValue by remember { mutableStateOf("") }
    var titleErrorState by remember { mutableStateOf(false) }
    fun validateInputTitle(){
        titleErrorState = titleValue.isBlank()
    }

    var messageValue by remember { mutableStateOf("") }
    var messageErrorState by remember { mutableStateOf(false) }
    fun validateInputMessage(){
        messageErrorState = messageValue.isBlank()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Notification") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.background),
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(12.dp)
                            .noRippleClickable { navigateBack() }
                    )
                },
                actions = {
                    Icon(
                        Icons.Default.SaveAs,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(24.dp)
                            .noRippleClickable {

                                //Validate inputs are legal
                                validateInputTitle()
                                validateInputMessage()

                                if (titleErrorState && messageErrorState) {
                                    createNotificationViewModel.createNotification(
                                        titleValue, messageValue)
                                }
                            }
                    )
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopStart
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    item {
                        GetTitle(
                            updateTitle = {
                                if (titleValue.length <= titleCharacterLimit) titleValue = it
                            },
                            title =  titleValue,
                            isError = titleErrorState

                        )
                    }

                    item {
                        GetMessage(
                            updateMessage = {
                                if (messageValue.length <= messageCharacterLimit) messageValue = it
                            },
                            message = messageValue,
                            isError = messageErrorState
                        )
                    }
                }
            }
        }
    )
}

/**
 * Custom modifier to remove the ripple effect seen in onClicks
 */
private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    then(
        clickable(
            indication = null, interactionSource = remember { MutableInteractionSource() }
        ){
            onClick()
        }
    )
}

