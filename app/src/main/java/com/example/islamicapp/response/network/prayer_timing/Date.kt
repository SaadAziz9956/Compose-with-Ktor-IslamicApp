package com.example.islamicapp.response.network.prayer_timing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val gregorian: String,
    val hijri: String,
    val timestamp: Int
)