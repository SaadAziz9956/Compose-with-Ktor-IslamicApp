package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class DesignationX(
    val abbreviated: String,
    val expanded: String
)