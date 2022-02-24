package com.example.islamicapp.ui.chapters.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.App
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel
@Inject
constructor(
    private val dbRepo: DatabaseRepository,
    private val context: App
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getChapters()
        }
    }

    private val _events = mutableStateOf<EventHandler>(EventHandler.Idle)
    val events: State<EventHandler> = _events

    private fun getChapters() {
        Timber.d("getChapters")
        dbRepo.getAllChapters()
            .onEach {
                Timber.d("Viewmodel started")
                _events.value = EventHandler.Chapters(it)
            }.launchIn(viewModelScope)
    }

    fun changeScreen(chapter: Surah) {
        _events.value = EventHandler.Chapter(chapter)
    }

}
