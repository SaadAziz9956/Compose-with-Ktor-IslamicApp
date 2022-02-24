package com.example.islamicapp.response.network.chapter_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class ChapterInfo(
    val chapter_id: Int? = null,
    val id: Int? = null,
    val language_name: String? = null,
    val short_text: String? = null,
    val source: String? = null,
    val text: String? = null
)