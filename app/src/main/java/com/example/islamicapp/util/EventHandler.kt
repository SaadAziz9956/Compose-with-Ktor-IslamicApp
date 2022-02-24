package com.example.islamicapp.util

import android.content.Intent
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.book_response.Surah


sealed class EventHandler {

    object Idle : EventHandler()

    data class Error(
        val error: String? = null
    ) : EventHandler()

    data class MoveForward(
        val intent: Intent? = null
    ) : EventHandler()

    data class PlayAudio(
        val ayah: Ayah? = null
    ) : EventHandler()

    data class Chapters(
        val chapters: List<Surah>? = null
    ) : EventHandler()

    data class Chapter(
        val chapter: Surah? = null
    ) : EventHandler()


    object PauseAudio : EventHandler()

}