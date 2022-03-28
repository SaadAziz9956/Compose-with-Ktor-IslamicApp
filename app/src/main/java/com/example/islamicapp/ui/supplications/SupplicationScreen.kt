package com.example.islamicapp.ui.supplications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.R
import com.example.islamicapp.ui.supplications.components.Category
import com.example.islamicapp.ui.supplications.viewmodel.SupplicationViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme
import kotlinx.coroutines.delay

@Composable
fun SupplicationScreen() {

    val supplicationViewModel: SupplicationViewModel = hiltViewModel()

    val duas = supplicationViewModel.dua.value

    Surface(
        Modifier
            .padding(bottom = 55.dp)
            .fillMaxSize(1f)
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Column {

            Row(
                Modifier
                    .fillMaxWidth(),
            ) {

                Text(
                    text = "Supplications",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(
                            R.font.ralewaymedium
                        )
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )

            }

            duas.forEach { supplication ->

                Category(supplication)

            }

        }

    }
}


@Preview
@Composable
fun PreviewQiblaScreen() {
    IslamicAppTheme {
        SupplicationScreen()
    }
}