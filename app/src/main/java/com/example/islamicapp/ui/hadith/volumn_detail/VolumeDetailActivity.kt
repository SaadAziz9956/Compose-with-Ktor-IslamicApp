package com.example.islamicapp.ui.hadith.volumn_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.islamicapp.ui.hadith.viewmodel.HadithViewModel
import com.example.islamicapp.ui.hadith.volumn_detail.screen.HadithBookVolume
import com.example.islamicapp.ui.theme.IslamicAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VolumeDetailActivity : ComponentActivity() {

    private val viewModel by viewModels<HadithViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            viewModel.getSpecificVolume(intent)

            IslamicAppTheme {
                    HadithBookVolume() {
                        finish()
                    }
            }
        }
    }

}