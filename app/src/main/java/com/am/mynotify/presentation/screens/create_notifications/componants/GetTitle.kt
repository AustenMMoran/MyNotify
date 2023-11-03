package com.am.mynotify.presentation.screens.create_notifications.componants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun GetTitle(
    updateTitle: (String) -> Unit,
    title: String,
    isError: Boolean
) {
    OutlinedTextField(
        value = title,
        label = { Text(text = "Title") },
        placeholder = { Text(text = "Give it a name...") },
        maxLines = 1,
        onValueChange = {
            updateTitle(it)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = isError,
        supportingText = {
            if(isError){
                Text(text = "Enter a valid title")
            }
        },
        trailingIcon = {
            if(isError){
                Icon(
                    Icons.Default.Error,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}