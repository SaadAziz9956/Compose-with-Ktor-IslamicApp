package com.example.islamicapp.ui.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensemanagment.R

@Composable
fun MainCard(
    nextPrayer: String,
    islamicDate: String,
    prayerTime: String,
    dayOfTheWeek: String,
    city: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ) {

        Image(
            painter = painterResource(id = R.drawable.backgroundimage), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        end = 20.dp,
                        top = 15.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Now",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.ralewayregular
                            )
                        )
                    )

                    Text(
                        text = prayerTime,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp,
                        ),

                        )

                    Text(
                        text = "Next Prayer",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.ralewayregular
                            )
                        )
                    )

                    Text(
                        text = nextPrayer,
                        color = Color.White,
                        fontSize = 18.sp,

                        )
                }

                Column (horizontalAlignment = Alignment.CenterHorizontally) {

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic__bell),
                            contentDescription = "",
                            modifier = Modifier.padding(
                                end = 15.dp,
                            )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_share_white),
                            contentDescription = "",

                            )
                    }

                    Text(
                        text = city,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.ralewaymedium
                            )
                        ), modifier = Modifier.padding(top = 10.dp)
                    )
                }

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = dayOfTheWeek,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.ralewayregular
                            )
                        )
                    )

                    Text(
                        text = islamicDate,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.ralewaymedium
                            )
                        )
                    )

                }

            }

        }
    }
}
