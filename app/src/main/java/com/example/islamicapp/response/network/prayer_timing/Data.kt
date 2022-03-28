package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val date: Date,
    val meta: Meta,
    val timings: Timings
)