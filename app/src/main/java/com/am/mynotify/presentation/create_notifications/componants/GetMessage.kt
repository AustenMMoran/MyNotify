package com.am.mynotify.presentation.create_notifications.componants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetMessage(
    updateMessage: (String) -> Unit,
    message: String,
    isError: Boolean
) {
    OutlinedTextField(
        value = message,
        label = { Text(text = "Message") },
        placeholder = { Text(text = "Write your prompt...") },
        onValueChange = {
            updateMessage(it)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        isError = isError,
        supportingText = {
            if(isError){
                Text(text = "Enter a valid message")
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}