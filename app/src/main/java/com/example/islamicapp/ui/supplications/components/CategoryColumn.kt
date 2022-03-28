package com.example.islamicapp.ui.supplications.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.R
import com.example.islamicapp.response.local.duaas.Dua
import com.example.islamicapp.ui.supplications.detail.DuaDetails
import com.example.islamicapp.ui.theme.LightBackground
import com.example.islamicapp.ui.theme.MidGrey

@Composable
fun CategoryColumn(item: Dua, index: Int) {

    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                bottom = 5.dp
            )
            .background(
                color = LightBackground,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                context.startActivity(Intent(context, DuaDetails::class.java).also {
                    it.putExtra("DuaItem", item)
                })
            }
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp,
                    horizontal = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
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

            Text(
                text = item.name,
                fontSize = 17.sp,
                color = Black,
                modifier = Modifier.padding(start = 10.dp)
            )

        }

    }

}

