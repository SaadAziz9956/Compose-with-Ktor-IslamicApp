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
import com.example.islamicapp.ui.hadith.volumn_detail.viewmodel.VolumeDetailViewModel
import com.example.islamicapp.ui.verse.screens.ToolbarScreen
import com.example.islamicapp.util.Constants.HADITH_BOOK

@Composable
fun HadithBookVolume(
    volumeDetailViewModel: VolumeDetailViewModel = viewModel(),
    backPress: () -> Unit
) {

    val hadeesBookItem = volumeDetailViewModel.volume.value

    val context = LocalContext.current

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxHeight()) {

            ToolbarScreen(chapterName = hadeesBookItem.name) {
                backPress()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                LazyColumn {

                    itemsIndexed(items = hadeesBookItem.books) { _, item ->
                        Surface(
                            modifier = Modifier
                                .clickable
                                {
                                    val intent = Intent(context, HadithBookDetails::class.java)
                                    intent.putExtra(HADITH_BOOK, item)
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