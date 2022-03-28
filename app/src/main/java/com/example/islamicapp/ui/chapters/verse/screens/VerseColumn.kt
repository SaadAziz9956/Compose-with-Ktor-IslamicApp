package com.example.islamicapp.ui.chapters.verse.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.R
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.ui.theme.*
import com.example.islamicapp.ui.chapters.verse.viewmodel.VerseViewModel

@Composable
fun VerseColumn(
    ayah: Ayah?,
    verseViewModel: VerseViewModel = viewModel()
) {

    var text by remember { mutableStateOf("Ayah in Arbi") }
    var textTranslated by remember { mutableStateOf("Ayah Translated") }
    val context = LocalContext.current

    ayah?.text?.let {
        text = it
    }

    ayah?.englishTrans?.let {
        textTranslated = it
    }

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MidColorBackground,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = CardBackgroundGradientTwo,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(5.dp)
            ) {

                Text(
                    text = ayah?.numberInSurah.toString(),
                    fontSize = 8.sp,
                    color = LightBackground,
                    fontFamily = FontFamily.Default
                )

            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Please wait! Audio is loading.",
                                    Toast.LENGTH_LONG
                                )
                                .show()
                            verseViewModel.openBottomSheet(ayah)
                            verseViewModel.playAudio(ayah)
                        }
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                    end = 10.dp,
                    start = 10.dp
                ),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = text,
                fontSize = 33.sp,
                color = Color.Black,
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
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )
        }

        Divider(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 5.dp
                )
                .background(
                    color = ExtraLightGrey
                )
                .height(1.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPrevieww() {
    IslamicAppTheme {
        VerseColumn(null)
    }
}