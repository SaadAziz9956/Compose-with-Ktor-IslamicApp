package com.example.islamicapp.ui.chapters

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
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
import com.example.islamicapp.ui.chapters.components.ColumnItem
import com.example.islamicapp.ui.chapters.verse.VerseActivity
import com.example.islamicapp.ui.chapters.viewmodel.ChapterViewModel
import com.example.islamicapp.ui.theme.MidGrey
import com.example.islamicapp.util.Constants
import com.example.islamicapp.util.EventHandler
import kotlinx.coroutines.delay
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
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

    Scaffold() {

    }

    LaunchedEffect(Unit) {
        delay(100)
        showScreen = true
    }

    if (showScreen) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LazyColumn {

                item {
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
                }

                stickyHeader {
                    Box(
                        Modifier
                            .background(
                                color = Color.White
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                start = 15.dp,
                                bottom = 4.dp
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
                    }
                }

                itemsIndexed(items = chaptersList) { _, item ->
                    Box(
                        modifier = Modifier
                            .clickable
                            {
                                val intent = Intent(context, VerseActivity::class.java)
                                intent.putExtra(Constants.CHAPTER, item)
                                context.startActivity(intent)
                            }
                    ) {

                        ColumnItem(item)

                    }
                }
            }

        }
    }

}