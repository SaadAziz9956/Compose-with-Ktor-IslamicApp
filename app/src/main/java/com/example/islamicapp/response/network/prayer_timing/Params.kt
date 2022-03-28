package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Params(
    val Fajr: Int,
    val Isha: Int
)