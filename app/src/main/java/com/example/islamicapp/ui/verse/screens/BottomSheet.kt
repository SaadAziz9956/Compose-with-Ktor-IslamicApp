package com.example.islamicapp.ui.verse.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.R
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.ui.theme.CardBackgroundGradientTwo
import com.example.islamicapp.ui.theme.LightBackground
import com.example.islamicapp.ui.verse.viewmodel.VerseViewModel

@Composable
fun BottomSheet(
    ayah: Ayah?,
    verseViewModel: VerseViewModel = viewModel()
) {

    var text by remember {
        mutableStateOf("Verse")
    }

    ayah?.text?.let {
        text = it
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = CardBackgroundGradientTwo,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
            .padding(
                horizontal = 15.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 20.dp),
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LightBackground
        )

        Image(
            painter = painterResource(id = R.drawable.ic_pause), contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    verseViewModel.pauseAudio()
                }
        )
    }

}