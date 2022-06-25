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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.R
import com.example.islamicapp.ui.home.viewmodel.MainViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme

@Composable
fun BookTab(
    mainViewModel: MainViewModel
) {
    var text by remember {
        mutableStateOf("")
    }

    var textTranslated by remember {
        mutableStateOf("")
    }

    val chapterName = mainViewModel.chapterName.value

    val chapterNum = mainViewModel.chapterNum.value

    val verse = mainViewModel.verse.value

    verse.text?.let {
        text = it
    }

    verse.englishTrans?.let {
        textTranslated = it
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
                    text = "Verse",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.ralewaymedium)
                    )
                )

                Text(
                    text = "Surah $chapterName [$chapterNum-${verse.numberInSurah}]",
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
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = text,
                fontSize = 25.sp,
                textAlign = TextAlign.End,
                fontFamily = FontFamily(
                    Font(R.font.almajeed)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp,
                    bottom = 5.dp,
                    end = 10.dp
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = textTranslated,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )
        }
    }

}