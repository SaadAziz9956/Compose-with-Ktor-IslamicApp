package com.example.islamicapp.ui.hadith.volumn_detail.screen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.ui.hadith.hadith_book_detail.HadithBookDetails
import com.example.islamicapp.ui.hadith.viewmodel.HadithViewModel
import com.example.islamicapp.ui.verse.screens.ToolbarScreen
import com.example.islamicapp.util.Constants.HADITH_BOOK

@Composable
fun HadithBookVolume(
    hadithBookViewModel: HadithViewModel = viewModel(),
    backPress: () -> Unit
) {

    val hadithBookItem = hadithBookViewModel.volume.value

    val context = LocalContext.current

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxHeight()) {

            ToolbarScreen(chapterName = hadithBookItem.name) {
                backPress()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                LazyColumn {

                    itemsIndexed(items = hadithBookItem.books) { _, item ->
                        Surface(
                            modifier = Modifier
                                .clickable
                                {
                                    val intent = Intent(context, HadithBookDetails::class.java)
                                    intent.putExtra(HADITH_BOOK, item.name)
                                    context.startActivity(intent)
                                }
                        ) {

                            HadithBookVolumeColumnItem(item)

                        }
                    }
                }

            }

        }

    }

}