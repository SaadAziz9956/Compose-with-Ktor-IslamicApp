package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Designation(
    val abbreviated: String,
    val expanded: String
)