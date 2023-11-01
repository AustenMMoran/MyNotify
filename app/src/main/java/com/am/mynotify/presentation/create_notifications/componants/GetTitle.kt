package com.am.mynotify.presentation.create_notifications.componants

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
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