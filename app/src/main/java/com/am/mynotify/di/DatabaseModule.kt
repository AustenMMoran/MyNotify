package com.am.mynotify.di

import android.content.Context
import androidx.room.Room
import com.am.mynotify.data.database.NotificationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Module to provide the application with a room database.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): NotificationDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NotificationDatabase::class.java,
            name = "notification_db",
        ).build()
    }
}