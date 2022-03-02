package com.example.islamicapp.ui.hadith

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.R
import com.example.islamicapp.ui.hadith.screen.HadithBookScreen
import com.example.islamicapp.ui.hadith.viewmodel.HadithViewModel
import com.example.islamicapp.ui.theme.MidGrey

@Composable
fun MainHadithScreen() {

    val viewModel: HadithViewModel = hiltViewModel()

    val books = viewModel.books.value


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            modifier = Modifier.padding(
                top = 30.dp,
                start = 15.dp
            ),
            text = "Hadith Book",
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
            text = "Sahih Bukhari",
            fontSize = 25.sp,
            color = Color.Black,
            fontFamily = FontFamily(
                Font(
                    R.font.ralewaymedium
                )
            )
        )

        HadithBookScreen(books)

    }
}
