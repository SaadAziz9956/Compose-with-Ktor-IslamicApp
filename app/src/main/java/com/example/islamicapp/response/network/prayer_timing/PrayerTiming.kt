package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class PrayerTiming(
    val code: Int,
    val results: Results,
    val status: String
)