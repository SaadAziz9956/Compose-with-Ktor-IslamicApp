package com.example.islamicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.islamicapp.ui.main.screens.BottomNavScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {
                BottomNavScreen()
            }
        }
    }
}
