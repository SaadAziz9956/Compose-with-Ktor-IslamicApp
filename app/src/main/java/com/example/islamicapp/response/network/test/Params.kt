package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Params(
    val Fajr: Int,
    val Isha: Int
)