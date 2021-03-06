package com.example.islamicapp.di

import com.example.islamicapp.App
import com.example.islamicapp.repository.*
import com.example.islamicapp.response.network.KtorInterface
import com.example.islamicapp.room.dao.Daos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabaseRepository(
        dao: Daos
    ): DatabaseRepository {
        return DatabaseRepository(dao)
    }

    @Singleton
    @Provides
    fun provideChapterDetailsRepository(
        api: KtorInterface
    ): ChapterDetailsRepository {
        return ChapterDetailsRepository(api)
    }

    @Singleton
    @Provides
    fun provideJsonRepository(
        context: App,
        dbRepo: DatabaseRepository
    ): JsonRepository {
        return JsonRepository(context, dbRepo)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(
        dbRepo: DatabaseRepository,
        client: KtorInterface
    ): PrayerTimingRepository {
        return PrayerTimingRepository(client, dbRepo)
    }
    
}