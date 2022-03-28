package com.example.islamicapp.ui.hadith.hadith_book_detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.islamicapp.response.local.duaas.DuaaData
import com.example.islamicapp.response.local.hadess_book_response.Hadith
import com.example.islamicapp.ui.theme.CardBackgroundGradientTwo
import com.example.islamicapp.ui.theme.MidColorBackground

@Composable
fun HadithColumn(item: Any, index: Int) {

    when(item) {

        is Hadith -> {
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MidColorBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            vertical = 3.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "${index + 1}.  ${item.by}",
                            fontSize = 15.sp,
                            color = CardBackgroundGradientTwo,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 10.dp),
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
                    }


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp,
                            bottom = 25.dp,
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

            }
        }

        is DuaaData -> {
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MidColorBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            vertical = 3.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "${index + 1}.",
                            fontSize = 15.sp,
                            color = CardBackgroundGradientTwo,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 10.dp),
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
                    }


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 5.dp
                        ),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = item.text,
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
                            top = 5.dp,
                            bottom = 10.dp,
                            start = 5.dp
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = item.meaning,
                        fontSize = 17.sp,
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
                            top = 5.dp,
                            bottom = 25.dp,
                            start = 5.dp
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Reference : ${item.reference}",
                        fontSize = 13.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.ralewaysemibold)
                        )
                    )
                }

            }
        }

    }



}