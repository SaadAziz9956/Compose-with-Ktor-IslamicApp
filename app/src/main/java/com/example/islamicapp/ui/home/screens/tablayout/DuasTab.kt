package com.example.islamicapp.ui.home.screens.tablayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.example.islamicapp.ui.home.viewmodel.MainUiState

@Composable
fun DuasTab(
    uiState: MainUiState
) {

    var randDua by remember {
        mutableStateOf(DuaaData("", "", "", ""))
    }

    var duaName by remember {
        mutableStateOf("")
    }
    var duaType by remember {
        mutableStateOf("")
    }
    when (uiState) {
        is MainUiState.HasData -> {
            randDua = uiState.randDua!!
            duaName = uiState.duaName.toString()
            duaType = uiState.duaType.toString()
        }
        is MainUiState.NoData -> Unit
    }

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Supplication",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.ralewaymedium)
                    )
                )

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share_black),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = duaType,
                fontSize = 13.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewaysemibold)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 2.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = duaName,
                fontSize = 13.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(
                    Font(R.font.ralewaysemibold)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.End
        ) {
            randDua.text?.let {
                Text(
                    text = it,
                    fontSize = 25.sp,
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(
                        Font(R.font.almajeed)
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            randDua.meaning?.let {
                Text(
                    text = it,
                    fontSize = 15.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp,
                    bottom = 5.dp,
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            randDua.reference?.let {
                Text(
                    text = "Reference : $it",
                    fontSize = 13.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(
                        Font(R.font.ralewaysemibold)
                    )
                )
            }
        }

    }

}