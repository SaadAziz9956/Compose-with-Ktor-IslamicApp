package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Method(
    val id: Int,
    val location: Location,
    val name: String,
    val params: Params
)