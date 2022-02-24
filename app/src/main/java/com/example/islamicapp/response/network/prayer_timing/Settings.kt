package com.example.islamicapp.response.network.prayer_timing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val fajr_angle: Double,
    val highlat: String,
    val isha_angle: Double,
    val juristic: String,
    val school: String,
    val timeformat: String
)