package com.example.islamicapp.ui.chapters.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.response.local.duaas.Dua
import com.example.islamicapp.response.local.duaas.DuaaData
import com.example.islamicapp.ui.theme.AppBackground
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.ui.theme.LightBackground
import com.example.islamicapp.ui.theme.MidGrey

@Composable
fun ColumnItem(item: Surah) {

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
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 5.dp,
                bottom = 10.dp
            )
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "",
                )
                Box(Modifier.align(Alignment.Center)) {
                    Text(
                        text = item.number.toString(),
                        fontSize = 7.sp,
                        color = MidGrey,
                        fontFamily = FontFamily.Default
                    )
                }
            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = item.englishName,
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(
                            R.font.ralewayregular
                        )
                    )
                )

                Text(
                    text = item.revelationType + " - " + item.ayahs.size + " Ayahs",
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

        Text(
            text = item.name,
            fontSize = 25.sp,
            color = MidGrey,
            fontFamily = FontFamily(
                Font(R.font.uthmanicregular)
            ),
            modifier = Modifier.padding(end = 10.dp)
        )

    }

}


@Preview
@Composable
fun DefaultPreview() {
    val chaptersList = mutableListOf<Surah>()
    IslamicAppTheme {
        ColumnItem(chaptersList[1])
    }
}