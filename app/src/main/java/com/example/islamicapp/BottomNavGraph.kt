package com.example.islamicapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensemanagment.screens.ReportScreen
import com.example.expensemanagment.screens.TransactionScreen
import com.example.expensemanagment.screens.dashboard.DashboardScreen
import com.example.expensemanagment.ui.chapters.MainChapterScreen
import com.example.expensemanagment.ui.main.screens.MainScreen

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