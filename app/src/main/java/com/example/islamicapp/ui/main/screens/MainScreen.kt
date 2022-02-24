package com.example.islamicapp.ui.main.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.ui.main.viewmodel.MainViewModel
import com.example.islamicapp.ui.permission.LocationPermissionActivity
import com.example.islamicapp.ui.theme.AppBackground

@Composable
fun MainScreen() {

    val context = LocalContext.current

    val viewModel: MainViewModel = hiltViewModel()

    val nextPrayer = viewModel.nextPrayer.value

    val islamicDate = viewModel.islamicDate.value

    val prayerTime = viewModel.now.value

    val dayOfTheWeek = viewModel.currentDay.value

    val city = viewModel.cityState.value

    val intent = viewModel.intent.value

    if (intent) {
        Intent(context, LocationPermissionActivity::class.java).also {
            context.startActivity(it)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = AppBackground
            )
    ) {

        MainCard(nextPrayer, islamicDate, prayerTime, dayOfTheWeek, city)

    }
}