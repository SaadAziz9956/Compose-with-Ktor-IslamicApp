package com.example.islamicapp.ui.hadith.screen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.ui.hadith.volumn_detail.VolumeDetailActivity
import com.example.islamicapp.util.Constants.VOLUME

@Composable
fun HadithBookScreen(
    books: List<HadeesBookItem>,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn {

            itemsIndexed(items = books) { index, item ->
                Surface(
                    modifier = Modifier
                        .clickable
                        {
                            val intent = Intent(context, VolumeDetailActivity::class.java)
                            intent.putExtra(VOLUME, item.name)
                            context.startActivity(intent)
                        }
                ) {

                    HadithColoumItem(item, index)

                }
            }
        }

    }

}