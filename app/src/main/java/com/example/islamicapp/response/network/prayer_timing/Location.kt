package com.example.islamicapp.response.network.prayer_timing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val city: String,
    val country: String,
    val country_code: String,
    val elevation: Double,
    val latitude: Double,
    val local_offset: Double,
    val longitude: Double,
    val timezone: String
)