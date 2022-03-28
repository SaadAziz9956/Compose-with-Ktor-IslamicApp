package com.example.islamicapp.ui.supplications.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.islamicapp.response.local.duaas.Dua
import com.example.islamicapp.ui.supplications.detail.components.DuaDetailScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme

class DuaDetails: ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {

                val duaItem = intent.extras?.getParcelable<Dua>("DuaItem")

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (duaItem != null) {
                        DuaDetailScreen(duaItem) {
                            finish()
                        }
                    }
                }
            }
        }
    }

}