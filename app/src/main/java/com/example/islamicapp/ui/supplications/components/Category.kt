package com.example.islamicapp.ui.supplications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R
import com.example.islamicapp.response.local.duaas.Supplication
import com.example.islamicapp.ui.theme.CardBackgroundGradientTwo

@Composable
fun Category(item: Supplication) {

    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White
                )
        ) {

            Text(
                text = item.name,
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewaymedium
                    )
                ),
                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
            )

        }

    }

}