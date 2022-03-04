package com.example.islamicapp.ui.chapters.verse.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R

@Composable
fun ToolbarScreen(chapterName: String, finish: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier.clickable {
                finish()
            }
        )

        Text(
            text = chapterName,
            fontSize = 17.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp,
            bottom = 5.dp),
            fontFamily = FontFamily(
                Font(
                    R.font.ralewaymedium
                )
            )
        )

    }
}