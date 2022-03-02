package com.example.islamicapp.response.network.test

import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val code: Int,
    val `data`: List<Data>,
    val status: String
)