package com.example.islamicapp.ui.verse.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensemanagment.R
import com.example.expensemanagment.response.local.book_response.Surah
import com.example.expensemanagment.ui.theme.CardBackgroundGradientOne
import com.example.expensemanagment.ui.theme.CardBackgroundGradientTwo
import com.example.expensemanagment.ui.theme.IslamicAppTheme
import com.example.expensemanagment.ui.verse.viewmodel.VerseViewModel

@Composable
fun TopCard(chapter: Surah?) {

    var chapterName by remember { mutableStateOf("Chapter Name") }
    var nameMeaning by remember { mutableStateOf("Chapter Meaning") }
    var reveleation by remember { mutableStateOf("MECCAN  -   7 Verses") }

    val viewModel: VerseViewModel = hiltViewModel()

    chapter?.let {
        it.apply {

            chapterName = name
            nameMeaning = englishNameTranslation
            reveleation = "$revelationType  -  ${ayahs.size} Verses"

        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 10.dp,
                bottom = 15.dp
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        CardBackgroundGradientOne,
                        CardBackgroundGradientTwo
                    )
                ),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                chapter?.let { viewModel.intentLauncher(it) }
            },
        horizontalArrangement = Arrangement.Center,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = chapterName,
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(
                        R.font.uthmanicregular
                    )
                ),
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = nameMeaning,
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewayregular
                    )
                ),
                modifier = Modifier.padding(top = 5.dp)
            )

            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(250.dp)
                    .height(0.5.dp)
                    .background(
                        color = Color.White
                    )
            )

            Text(
                text = reveleation,
                fontSize = 10.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewayregular
                    )
                ),
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "﷽",
                fontSize = 35.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(
                        R.font.uthmanicregular
                    )
                ),
                modifier = Modifier.padding(
                    top = 30.dp,
                    bottom = 15.dp
                )
            )

        }

    }


}


@Preview
@Composable
fun DefaultPreview() {
    IslamicAppTheme {
        TopCard(null)
    }
}