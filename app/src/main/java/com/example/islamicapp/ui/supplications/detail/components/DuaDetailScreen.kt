package com.example.islamicapp.ui.supplications.detail.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.islamicapp.response.local.duaas.Dua
import com.example.islamicapp.ui.chapters.components.ColumnItem
import com.example.islamicapp.ui.chapters.verse.VerseActivity
import com.example.islamicapp.ui.chapters.verse.screens.ToolbarScreen
import com.example.islamicapp.ui.chapters.verse.screens.VerseColumn
import com.example.islamicapp.ui.hadith.hadith_book_detail.screen.HadithColumn
import com.example.islamicapp.util.Constants

@Composable
fun DuaDetailScreen(
    duaItem: Dua,
    finish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        ToolbarScreen(chapterName = duaItem.name) {
            finish()
        }

        DetailsScreen(duaItem)

    }

}

@Composable
fun DetailsScreen(duaItem: Dua) {

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {

        LazyColumn {

            itemsIndexed(items = duaItem.data) { index, item ->
                Box{

                    HadithColumn(item, index)

                }
            }
        }

    }

}