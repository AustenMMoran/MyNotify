package com.am.mynotify.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.am.mynotify.presentation.screens.create_notifications.CreateNotificationScreen
import com.am.mynotify.presentation.screens.create_notifications.CreateNotificationViewModel
import com.am.mynotify.presentation.screens.your_notifications.YourNotificationScreen
import com.am.mynotify.presentation.screens.your_notifications.YourNotificationsViewModel

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "your_notifications"
    ){
        yourNotifications(
            navToCreate = {
                navController.navigate("create_notification")
            }
        )

        createNotifications(
            navBack = {
                //Todo: Custom navigate options to remove requirement to double pop
                navController.popBackStack()
                navController.popBackStack()
                navController.navigate("your_notifications")
            }
        )

    }
}

fun NavGraphBuilder.yourNotifications(
    navToCreate: () -> Unit
){
    composable(
        route = "your_notifications",
        enterTransition = { fadeIn(animationSpec = tween(durationMillis = 500)) },
        exitTransition = { fadeOut(animationSpec = tween(durationMillis = 500)) }
    ){
        val viewModel = hiltViewModel<YourNotificationsViewModel>()
        YourNotificationScreen(
            viewModel,
            navigateToCreate = { navToCreate() }
        )
    }
}

fun NavGraphBuilder.createNotifications(
    navBack: () -> Unit
){
    composable(
        route = "create_notification",
        enterTransition = { fadeIn(animationSpec = tween(durationMillis = 500)) },
        exitTransition = { fadeOut(animationSpec = tween(durationMillis = 500)) }
    ){
        val viewModel = hiltViewModel<CreateNotificationViewModel>()

        CreateNotificationScreen(
            viewModel,
            navigateBack = { navBack() }
        )

    }
}