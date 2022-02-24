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
import com.example.expensemanagment.R
import com.example.expensemanagment.response.local.book_response.Surah
import com.example.expensemanagment.ui.chapters.components.ChaptersScreen
import com.example.expensemanagment.ui.chapters.viewmodel.ChapterViewModel
import com.example.expensemanagment.ui.theme.MidGrey
import com.example.expensemanagment.util.EventHandler
import timber.log.Timber

@Composable
fun MainChapterScreen() {

    val context = LocalContext.current

    val viewModel: ChapterViewModel = hiltViewModel()

    var chaptersList = mutableListOf<Surah>()


    when (val event = viewModel.events.value) {
        is EventHandler.Chapters -> {
            event.chapters?.toMutableList()?.let {
                Timber.d("Chapters : ${it.size}")
                chaptersList = it
            }
        }

        else -> {}
    }


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