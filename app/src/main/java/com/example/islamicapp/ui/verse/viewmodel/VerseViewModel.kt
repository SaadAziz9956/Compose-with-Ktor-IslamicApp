package com.example.islamicapp.ui.verse.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.App
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.ui.chapter_details.ChapterDetail
import com.example.islamicapp.util.EventHandler
import com.example.islamicapp.util.Constants.CHAPTER_NAME
import com.example.islamicapp.util.Constants.CHAPTER_NO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VerseViewModel
@Inject
constructor(
    private val context: App
) : ViewModel() {

    private var _event = MutableSharedFlow<EventHandler>()
    val event = _event.asSharedFlow()

    var bottomSheet = mutableStateOf(false)

    var verse = mutableStateOf(Ayah())

    fun playAudio(ayah: Ayah?) {

        Timber.d("Audio : ${ayah?.audio}")

        viewModelScope.launch {
            _event.emit(
                EventHandler.PlayAudio(
                    ayah
                )
            )
        }

    }

    fun intentLauncher(chapter: Surah) {
        viewModelScope.launch {
            Intent(context, ChapterDetail::class.java).also { intent ->
                Bundle().let { bundle ->
                    bundle.putInt(CHAPTER_NO, chapter.number)
                    bundle.putString(CHAPTER_NAME, chapter.englishName)
                    intent.putExtras(bundle)
                }
                _event.emit(
                    EventHandler.MoveForward(intent)
                )
            }
        }
    }

    fun openBottomSheet(ayah: Ayah?) {
        ayah?.let {
            verse.value = it
            bottomSheet.value = true
        }
    }

    fun pauseAudio() {
        viewModelScope.launch {
            bottomSheet.value = false
            _event.emit(
                EventHandler.PauseAudio
            )
        }
    }


}