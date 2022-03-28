package com.example.islamicapp.ui.home.screens.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.islamicapp.ui.chapters.MainChapterScreen
import com.example.islamicapp.ui.hadith.MainHadithScreen
import com.example.islamicapp.ui.home.screens.MainScreen
import com.example.islamicapp.ui.qibla.QiblaScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route
    ) {

        composable(route = BottomBarScreens.Home.route) {

            MainScreen()

        }

        composable(route = BottomBarScreens.Quran.route) {

            MainChapterScreen()

        }

        composable(route = BottomBarScreens.Hadith.route) {

            MainHadithScreen()

        }

        composable(route = BottomBarScreens.Supplications.route) {

            QiblaScreen()

        }

    }

}