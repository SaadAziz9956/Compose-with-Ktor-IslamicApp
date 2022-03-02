package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val latitude: Double,
    val latitudeAdjustmentMethod: String,
    val longitude: Double,
    val method: Method,
    val midnightMode: String,
    val offset: Offset,
    val school: String,
    val timezone: String
)