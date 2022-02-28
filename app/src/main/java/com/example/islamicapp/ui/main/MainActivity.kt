package com.example.islamicapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.islamicapp.ui.main.screens.bottomnav.BottomNavScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {
                BottomNavScreen()
            }
        }
    }
}


