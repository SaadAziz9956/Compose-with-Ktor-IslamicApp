package com.example.islamicapp.ui.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.islamicapp.R
import com.example.islamicapp.ui.theme.IslamicAppTheme

class OnBoardingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IslamicAppTheme {
                OnBoardingScreen()
            }
        }
    }

}

@Composable
fun OnBoardingScreen() {

    val isPlaying by remember {
        mutableStateOf(true)
    }

    val speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.loading)
    )

    // to control the animation
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false

    )

    Surface(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(400.dp)
            )

            Text(
                text = "Setting up for the first time",
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.ralewaymedium)
                )
            )


        }

    }
}