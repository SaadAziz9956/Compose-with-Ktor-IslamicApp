package com.example.islamicapp.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.islamicapp.R
import com.example.islamicapp.ui.splash.viewmodel.OnBoardingViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.util.EventHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel by viewModels<OnBoardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            viewModel.initViewModel()

            startCollecting()

            IslamicAppTheme {
                SplashScreen()
            }
        }
    }

    private fun startCollecting() {
        lifecycleScope.launchWhenCreated {

            viewModel.events
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { event ->

                    when (event) {

                        is EventHandler.MoveForward -> {
                            startActivity(event.intent)
                            finish()
                        }

                        else -> {}
                    }

                }

        }
    }
}

@Composable
private fun SplashScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
    ) {
        Box {
            SetImage()
        }
    }
}

@Composable
private fun SetImage() {

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
            }
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(R.drawable.app_icon),
            contentDescription = "",
        )
        Text(
            text = "Islamic App",
            style = MaterialTheme.typography.body2,
            fontSize = 17.sp,
            modifier = Modifier.padding(10.dp)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Loading",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(10.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_spinner),
            contentDescription = "",
            modifier = Modifier.graphicsLayer {
                rotationZ = angle
            }

        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IslamicAppTheme {
        SplashScreen()
    }
}