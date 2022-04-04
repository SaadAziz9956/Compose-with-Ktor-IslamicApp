package com.example.islamicapp.util

import android.content.Context
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.repository.PrayerTimingRepository

interface DependencyContainer {
    val postsRepository: PrayerTimingRepository
    val interestsRepository: DatabaseRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : DependencyContainer {

    override val postsRepository: PrayerTimingRepository by lazy {
        PrayerTimingRepository()
    }

    override val interestsRepository: DatabaseRepository by lazy {
        DatabaseRepository()
    }
    
}