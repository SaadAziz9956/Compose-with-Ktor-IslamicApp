package com.example.islamicapp.ui.chapters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.R
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.ui.chapters.components.ChaptersScreen
import com.example.islamicapp.ui.chapters.viewmodel.ChapterViewModel
import com.example.islamicapp.ui.theme.MidGrey
import com.example.islamicapp.util.EventHandler
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun MainChapterScreen() {

    val context = LocalContext.current

    val viewModel: ChapterViewModel = hiltViewModel()

    var chaptersList = mutableListOf<Surah>()

    var showScreen by remember {
        mutableStateOf(false)
    }

    when (val event = viewModel.events.value) {
        is EventHandler.Chapters -> {
            event.chapters?.toMutableList()?.let {
                Timber.d("Chapters : ${it.size}")
                chaptersList = it
            }
        }

        else -> {}
    }

    LaunchedEffect(Unit) {
        delay(300)
        showScreen = true
    }

    if (showScreen) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Text(
                modifier = Modifier.padding(
                    top = 30.dp,
                    start = 15.dp
                ),
                text = "Al-Quran",
                fontSize = 13.sp,
                color = MidGrey,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewayregular
                    )
                )
            )

            Text(
                modifier = Modifier.padding(
                    start = 15.dp,
                    bottom = 10.dp
                ),
                text = "Surahs",
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewaymedium
                    )
                )
            )

            ChaptersScreen(chaptersList, context)

        }
    }

}