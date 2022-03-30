package com.example.islamicapp.ui.home.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.ui.home.screens.tablayout.*
import com.example.islamicapp.ui.home.viewmodel.MainUiState
import com.example.islamicapp.ui.home.viewmodel.MainViewModel
import com.example.islamicapp.ui.permission.LocationPermissionActivity
import com.example.islamicapp.ui.theme.AppBackground

@Composable
fun MainScreen() {

    var tabPage by remember {
        mutableStateOf(TabPage.Book)
    }

    val scrollState = rememberScrollState(0)

    val context = LocalContext.current

    val viewModel: MainViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsState().value

    var islamicDate by remember {
        mutableStateOf("")
    }

    var prayerTime by remember {
        mutableStateOf("")
    }

    var dayOfTheWeek by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    var intent by remember {
        mutableStateOf(false)
    }

    var nextPrayer by remember {
        mutableStateOf("")
    }

    when (uiState) {
        is MainUiState.HasData -> {
            islamicDate = uiState.islamicDate.toString()
            nextPrayer = uiState.nextPrayer.toString()
            prayerTime = uiState.prayerTiming.toString()
            dayOfTheWeek = uiState.currentDay.toString()
            city = uiState.city.toString()
            intent = uiState.intent == true
        }
        is MainUiState.NoData -> Unit
    }

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
            .padding(bottom = 55.dp)
            .verticalScroll(scrollState)
    ) {

        MainCard(nextPrayer, islamicDate, prayerTime, dayOfTheWeek, city)

        TabHome(selectedTabIndex = tabPage.ordinal, onSelectedTab = { tabPage = it })

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                ),
            shape = RoundedCornerShape(4.dp),
            elevation = 0.dp
        ) {

            when (tabPage) {
                TabPage.Book -> {
                    BookTab(uiState)
                }
                TabPage.Hadith -> {
                    HadithTab(uiState)
                }
                TabPage.Names -> {
                    NamesTab(uiState)
                }
                TabPage.Dua -> {
                    DuasTab(uiState)
                }
            }

        }
    }
}

