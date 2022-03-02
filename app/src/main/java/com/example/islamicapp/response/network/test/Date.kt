package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)