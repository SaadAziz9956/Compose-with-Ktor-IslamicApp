package com.example.islamicapp.ui.hadith.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.ui.theme.LightBackground
import com.example.islamicapp.ui.theme.MidGrey

@Composable
fun HadithColumnItem(item: HadeesBookItem, index: Int) {

    val animatedProgress = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    val animationModifier = Modifier
        .fillMaxWidth()
        .padding(
            vertical = 5.dp,
            horizontal = 5.dp
        )
        .background(
            color = LightBackground,
            shape = RoundedCornerShape(10.dp)
        )
        .alpha(animatedProgress.value)


    Row(
        modifier = animationModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 5.dp,
                bottom = 10.dp
            )
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "",
                )
                Text(
                    text = (index + 1).toString(),
                    fontSize = 7.sp,
                    color = MidGrey,
                    fontFamily = FontFamily.Default
                )

            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = item.name,
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(
                            R.font.ralewayregular
                        )
                    )
                )

                Text(
                    text = "${item.books.size} Chapters",
                    fontSize = 10.sp,
                    color = MidGrey,
                    fontFamily = FontFamily(
                        Font(
                            R.font.ralewayregular
                        )
                    )
                )
            }
        }

    }
}