package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class DesignationX(
    val abbreviated: String,
    val expanded: String
)