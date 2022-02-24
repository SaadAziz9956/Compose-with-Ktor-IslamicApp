package com.example.islamicapp.ui.chapters.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.expensemanagment.response.local.book_response.Surah
import com.example.expensemanagment.ui.verse.VerseActivity
import com.example.islamicapp.Constants.CHAPTER

@Composable
fun ChaptersScreen(chaptersList: MutableList<Surah>, context: Context) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn {

            itemsIndexed(items = chaptersList) { _, item ->
                Surface(
                    modifier = Modifier
                        .clickable
                        {
                            val intent = Intent(context, VerseActivity::class.java)
                            intent.putExtra(CHAPTER, item)
                            context.startActivity(intent)
                        }
                ) {

                    ColumnItem(item)

                }
            }
        }

    }

}