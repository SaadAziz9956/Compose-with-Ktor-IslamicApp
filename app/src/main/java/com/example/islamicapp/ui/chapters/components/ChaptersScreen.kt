package com.example.islamicapp.ui.chapters.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.ui.chapters.verse.VerseActivity
import com.example.islamicapp.ui.theme.AppBackground
import com.example.islamicapp.util.Constants.CHAPTER

@Composable
fun ChaptersScreen(chaptersList: MutableList<Surah>, context: Context) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn {

            itemsIndexed(items = chaptersList) { _, item ->
                Box(
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