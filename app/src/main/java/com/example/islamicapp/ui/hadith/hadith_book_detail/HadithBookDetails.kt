package com.example.islamicapp.ui.hadith.hadith_book_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.ui.hadith.hadith_book_detail.screen.HadithColumn
import com.example.islamicapp.ui.hadith.viewmodel.HadithViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.ui.chapters.verse.screens.ToolbarScreen
import com.example.islamicapp.util.Constants.HADITH_BOOK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithBookDetails : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val bookName = intent?.extras?.getString(HADITH_BOOK)

            IslamicAppTheme {
                HadithBookDetailsScreen(bookName) {
                    finish()
                }
            }
        }
    }

}

@Composable
fun HadithBookDetailsScreen(
    bookName: String?,
    hadithViewModel: HadithViewModel = viewModel(),
    finish: () -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    bookName?.let {
        title = it
    }

    hadithViewModel.getSpecificBook(bookName)

    val hadith = hadithViewModel.hadith.value

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxHeight()) {

            ToolbarScreen(chapterName = title) {
                finish()
            }

            LazyColumn() {
                itemsIndexed(items = hadith) { index, item ->
                    HadithColumn(item, index)
                }
            }

        }

    }
}