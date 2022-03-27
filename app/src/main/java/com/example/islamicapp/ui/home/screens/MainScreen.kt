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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.ui.home.screens.tablayout.*
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
            .padding(bottom =55.dp)
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
                    BookTab()
                }
                TabPage.Hadith -> {
                    HadithTab()
                }
                TabPage.Names -> {
                    NamesTab()
                }
                TabPage.Dua -> {
                    DuasTab()
                }
            }

        }
    }
}

