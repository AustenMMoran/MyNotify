package com.am.mynotify.di

import com.am.mynotify.data.database.NotificationDatabase
import com.am.mynotify.data.repository.NotificationRepositoryImpl
import com.am.mynotify.domain.repository.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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


}