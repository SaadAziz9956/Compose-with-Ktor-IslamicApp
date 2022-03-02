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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.islamicapp.response.local.hadess_book_response.Book
import com.example.islamicapp.ui.hadith.hadith_book_detail.screen.HadithColumn
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.ui.verse.screens.ToolbarScreen
import com.example.islamicapp.util.Constants.HADITH_BOOK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithBookDetails : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val book = intent?.extras?.getParcelable<Book>(HADITH_BOOK)

            IslamicAppTheme {
                book?.let {
                    HadithBookDetailsScreen(it) {
                        finish()
                    }
                }
            }
        }
    }

}

@Composable
fun HadithBookDetailsScreen(book: Book, finish: () -> Unit) {

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxHeight()) {

            ToolbarScreen(chapterName = book.name) {
                finish()
            }

            LazyColumn() {
                itemsIndexed(items = book.hadiths) { index, item ->
                    HadithColumn(item, index)
                }
            }

        }

    }
}