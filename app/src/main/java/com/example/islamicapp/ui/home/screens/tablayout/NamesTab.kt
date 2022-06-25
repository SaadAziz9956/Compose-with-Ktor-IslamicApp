package com.example.islamicapp.ui.home.screens.tablayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.R
import com.example.islamicapp.ui.home.viewmodel.MainViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme

@Composable
fun NamesTab(
    mainViewModel: MainViewModel
) {

    var englishNameMean by remember {
        mutableStateOf("")
    }

    var nameTafseer by remember {
        mutableStateOf("")
    }

    val nameData = mainViewModel.name.value

    nameData.meaning?.english?.let {
        englishNameMean = it
    }

    nameData.longDescription?.let {
        nameTafseer = it
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(
                color = Color.White
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp
                ),
            horizontalArrangement = Arrangement.End
        ) {

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
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = nameData.name,
                fontSize = 35.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.uthmanicregular)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = englishNameMean,
                fontSize = 17.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
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
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = nameTafseer,
                fontSize = 15.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )
        }

    }
}
