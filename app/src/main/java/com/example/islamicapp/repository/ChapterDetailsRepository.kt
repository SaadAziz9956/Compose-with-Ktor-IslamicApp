package com.example.islamicapp.repository

import com.example.islamicapp.response.network.KtorInterface
import com.example.islamicapp.response.network.chapter_detail_response.ChapterInfo
import com.example.islamicapp.util.DataState
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class ChapterDetailsRepository(
    private val client: KtorInterface,
) {

    suspend fun getChapterDetail(chapNo: Int): Flow<DataState<ChapterInfo>> = flow {
        try {
            val chapterDetails = client.getResponse(chapNo.toString())
            val details = chapterDetails.chapter_info
            Timber.d("Response : $details")
            emit(DataState.Success(details))
        } catch (e: Exception){
            Timber.d("Exception : ${e.message}")
            emit(DataState.Error(e))
        } catch (e: RedirectResponseException) {
            Timber.d("Exception : ${e.message}")
            emit(DataState.Error(e))
        } catch (e: ClientRequestException) {
            Timber.d("Exception : ${e.message}")
            emit(DataState.Error(e))
        } catch (e: ServerResponseException) {
            Timber.d("Exception : ${e.message}")
            emit(DataState.Error(e))
        }
    }

}

