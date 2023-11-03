package com.am.mynotify.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.am.mynotify.data.database.NotificationDatabase
import com.am.mynotify.data.repository.NotificationRepositoryImpl
import com.am.mynotify.domain.notification.MyNotificationManager
import com.am.mynotify.domain.notification.MyNotificationManagerImpl
import com.am.mynotify.domain.repository.NotificationRepository
import com.am.mynotify.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Generic Module to provide other aspects of the application
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNotificationRepository(
        db: NotificationDatabase
    ) : NotificationRepository {
        return NotificationRepositoryImpl(db.NotificationDao())
    }

    @Provides
    @Singleton
    fun provideMyNotificationManager(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ) : MyNotificationManager {
        return MyNotificationManagerImpl(notificationManager,builder)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideNotificationChannel() : NotificationChannel{
        val nc = NotificationChannel(
            Constants.CHANNEL_ID,
            Constants.CHANNEL_NAME,
            Constants.CHANNEL_IMPORTANCE
        )
        nc.description = Constants.CHANNEL_DESCRIPTION

        return nc
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context,
        channel: NotificationChannel
    ) : NotificationManager{

        val notificationManager: NotificationManager =
            context.getSystemService(
                Application.NOTIFICATION_SERVICE
            ) as NotificationManager

        notificationManager.createNotificationChannel(channel)

        return notificationManager
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        channel: NotificationChannel
    ) : NotificationCompat.Builder {
        val nb = NotificationCompat.Builder(
            context,
            channel.id
        )

        return nb.setContentTitle(context.applicationInfo.nonLocalizedLabel)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .setContentTitle(context.applicationInfo.nonLocalizedLabel)

    }
}