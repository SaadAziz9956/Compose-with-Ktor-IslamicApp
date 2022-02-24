package com.example.islamicapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class PrayerTimingEntity(
    val Asr: String? = null,
    val Dhuhr: String? = null,
    val Fajr: String? = null,
    val Imsak: String? = null,
    val Isha: String? = null,
    val Maghrib: String? = null,
    val Midnight: String? = null,
    val Sunrise: String? = null,
    val Sunset: String? = null,
    val gregorian: String? = null,
    @PrimaryKey
    val hijri: String,
    val timestamp: Int? = null,
    val city: String? = null,
)