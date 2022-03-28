package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)