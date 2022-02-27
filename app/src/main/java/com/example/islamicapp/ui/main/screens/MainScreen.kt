package com.example.islamicapp.ui.main.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.islamicapp.ui.main.screens.tablayout.BookTab
import com.example.islamicapp.ui.main.screens.tablayout.TabHome
import com.example.islamicapp.ui.main.screens.tablayout.TabPage
import com.example.islamicapp.ui.main.viewmodel.MainViewModel
import com.example.islamicapp.ui.permission.LocationPermissionActivity
import com.example.islamicapp.ui.theme.AppBackground

@Composable
fun MainScreen() {

    var tabPage by remember {
        mutableStateOf(TabPage.Book)
    }

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
        
        TabHome(selectedTabIndex = tabPage.ordinal, onSelectedTab = {tabPage = it})

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                ),
            shape = RoundedCornerShape(6.dp),
            elevation = 0.dp
        )  {

            when(tabPage) {
                TabPage.Book -> {
                    BookTab()
                }
                TabPage.Hadith -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.White
                            )
                            .height(250.dp)
                    ) {

                    }
                }
                TabPage.Names -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.White
                            )
                            .height(350.dp)
                    ) {

                    }
                }
                TabPage.Dua -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.White
                            )
                            .height(50.dp)
                    ) {

                    }
                }
            }

        }

    }

}