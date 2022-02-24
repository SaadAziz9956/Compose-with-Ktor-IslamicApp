package com.example.islamicapp.ui.main.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.islamicapp.ui.chapters.MainChapterScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Dashboard.route
    ) {

        composable(route = BottomBarScreens.Dashboard.route) {

            MainScreen()

        }

        composable(route = BottomBarScreens.Report.route) {

            MainChapterScreen()

        }

    }

}