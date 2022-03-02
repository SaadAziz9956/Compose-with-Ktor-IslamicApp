package com.example.islamicapp.ui.hadith.hadith_book_detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R
import com.example.islamicapp.response.local.hadess_book_response.Hadith
import com.example.islamicapp.ui.theme.CardBackgroundGradientTwo
import com.example.islamicapp.ui.theme.ExtraLightGrey
import com.example.islamicapp.ui.theme.LightBackground
import com.example.islamicapp.ui.theme.MidColorBackground

@Composable
fun HadithColumn(item: Hadith, index: Int) {

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MidColorBackground,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 5.dp,
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
                    text = (index + 1).toString(),
                    fontSize = 8.sp,
                    color = LightBackground
                )

            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp,
                    bottom = 5.dp,
                    start = 5.dp
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = item.by,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp,
                    bottom = 5.dp,
                    start = 5.dp
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = item.text,
                fontSize = 17.sp,
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