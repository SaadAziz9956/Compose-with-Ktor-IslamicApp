package com.example.islamicapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.islamicapp.ui.home.screens.bottomnav.BottomNavScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.util.ProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var providerFactory: ProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IslamicAppTheme {
                BottomNavScreen(providerFactory)
            }
        }
    }
}


