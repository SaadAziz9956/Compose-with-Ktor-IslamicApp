package com.example.islamicapp.ui.chapters.verse.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.example.islamicapp.response.local.book_response.Surah

@Composable
fun MainScreenVerse(
    chapter: Surah?,
    finish: () -> Unit
) {

    var chapterName by remember { mutableStateOf("Name") }

    chapter?.englishName?.let {
        chapterName = it
    }

    Column {

        ToolbarScreen(chapterName) {
            finish()
        }

        VerseRecycler(chapter)

    }

}
