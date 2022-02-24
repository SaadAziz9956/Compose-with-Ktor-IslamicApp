package com.example.islamicapp.ui.chapter_details.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanagment.repository.ChapterDetailsRepository
import com.example.expensemanagment.response.local.book_response.Surah
import com.example.expensemanagment.response.network.chapter_detail_response.ChapterInfo
import com.example.expensemanagment.util.DataState
import com.example.expensemanagment.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChapterDetailsViewModel
@Inject
constructor(
    private val repo: ChapterDetailsRepository,
) : ViewModel() {

    private var _event = MutableSharedFlow<EventHandler>()
    val event = _event.asSharedFlow()

    private var _chapterInfo = mutableStateOf(ChapterInfo())
    val chapterInfo: State<ChapterInfo> = _chapterInfo

    var chapNo: Int? = null

    fun initViewModel(chapterNo: Int?) {

        chapNo = chapterNo

        sendRequest(chapNo)

    }

    private fun sendRequest(chapNo: Int?) {

        viewModelScope.launch {

            chapNo?.let {
                repo.getChapterDetail(chapNo).onEach { dataState ->

                    when (dataState) {

                        is DataState.Success<ChapterInfo> -> {
                            Timber.d("Data : ${dataState.data}")
                            _chapterInfo.value = dataState.data
                        }

                        is DataState.Error -> {
                            Timber.d("Exception : ${dataState.exception.message}")
                            _event.emit(
                                EventHandler.Error(
                                    dataState.exception.message.toString()
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}