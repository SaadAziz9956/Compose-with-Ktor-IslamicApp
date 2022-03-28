package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)