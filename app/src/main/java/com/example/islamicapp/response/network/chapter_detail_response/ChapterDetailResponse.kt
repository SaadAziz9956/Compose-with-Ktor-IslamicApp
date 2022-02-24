package com.example.islamicapp.response.network.chapter_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class ChapterDetailResponse(
    val chapter_info: ChapterInfo
)