package com.example.islamicapp.di

import android.content.Context
import com.example.islamicapp.App
import com.example.islamicapp.room.AppDatabase
import com.example.islamicapp.room.dao.Daos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(
        @ApplicationContext app: Context
    ): App {
        return app as App
    }

    @Singleton
    @Provides
    fun providesRoomDb(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getDbInstance(context)
    }

    @Singleton
    @Provides
    fun provideDaos(
        database: AppDatabase
    ): Daos {
        return Daos(
            database.chapterDao(),
            database.prayerTiming(),
            database.hadithDao(),
            database.namesDao(),
            database.duaDao()
        )
    }

}