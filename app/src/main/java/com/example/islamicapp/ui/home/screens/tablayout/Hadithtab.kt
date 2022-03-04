package com.example.islamicapp.ui.home.screens.tablayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.R
import com.example.islamicapp.ui.home.viewmodel.MainViewModel

@Composable
fun HadithTab(
    mainViewModel: MainViewModel = viewModel()
) {

    var text by remember {
        mutableStateOf("")
    }

    val hadith = mainViewModel.hadith.value

    hadith.text.let {
        text = it
    }

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Sahih Bukhari",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 3.dp),
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.ralewaymedium)
                    )
                )

                Text(
                    text = hadith.info,
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share_black),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = hadith.by,
                fontSize = 13.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewaysemibold)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                fontSize = 15.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )
        }

    }

}