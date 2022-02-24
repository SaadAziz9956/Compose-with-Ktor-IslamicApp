package com.example.islamicapp.response.network

import com.example.expensemanagment.response.local.book_response.BookResponse
import com.example.expensemanagment.response.network.chapter_detail_response.ChapterDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("quran/{edition}")
    suspend fun wholeBook(
        @Path("edition") edition: String?
    ): BookResponse

    @GET("https://api.quran.com/api/v4/chapters/{chapter_id}/info")
    suspend fun getChapterDetail(
        @Path("chapter_id") edition: Int?,
        @Query("language") lang: String?
    ): ChapterDetailResponse

}