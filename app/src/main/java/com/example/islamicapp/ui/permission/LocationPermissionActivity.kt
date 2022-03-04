package com.example.islamicapp.ui.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.ui.home.MainActivity
import com.example.islamicapp.ui.theme.CardBackgroundGradientTwo
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.ui.theme.LightBackground
import timber.log.Timber


class LocationPermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .fillMaxWidth(1f)
                ) {
                    PermissionScreen()
                }
            }
        }
    }
}

@Composable
fun PermissionScreen() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Timber.d("Preciouse access granted")
                    Intent(context, MainActivity::class.java).also {
                        context.startActivity(it)
                    }
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Timber.d("Approximate location")
                    Intent(context, MainActivity::class.java).also {
                        context.startActivity(it)
                    }
                }
                else -> {
                    Timber.d("No location access granted")
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enable location permission",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {
                launcher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            modifier = Modifier.background(
                color = CardBackgroundGradientTwo
            )
        ) {
            Text(
                text = "Allow Permission",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = LightBackground
            )
        }

    }
}

private fun getLocation(context: Context) {

}

@Preview
@Composable
fun Previewww() {
    PermissionScreen()
}