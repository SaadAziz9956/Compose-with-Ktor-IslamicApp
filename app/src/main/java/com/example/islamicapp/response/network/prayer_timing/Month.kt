package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Month(
    val en: String,
    val number: Int
)