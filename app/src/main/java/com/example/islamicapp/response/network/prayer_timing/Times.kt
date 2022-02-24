package com.example.islamicapp.response.network.prayer_timing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Times(
    val Asr: String,
    val Dhuhr: String,
    val Fajr: String,
    val Imsak: String,
    val Isha: String,
    val Maghrib: String,
    val Midnight: String,
    val Sunrise: String,
    val Sunset: String
)