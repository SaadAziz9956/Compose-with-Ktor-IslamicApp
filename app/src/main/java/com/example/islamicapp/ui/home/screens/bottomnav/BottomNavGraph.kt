package com.example.islamicapp.ui.home.screens.bottomnav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.islamicapp.ui.chapters.MainChapterScreen
import com.example.islamicapp.ui.hadith.MainHadithScreen
import com.example.islamicapp.ui.home.screens.MainScreen
import com.example.islamicapp.ui.home.viewmodel.MainViewModel
import com.example.islamicapp.ui.supplications.SupplicationScreen
import com.example.islamicapp.util.ProviderFactory

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route
    ) {

        composable(route = BottomBarScreens.Home.route) {

            val homeViewModel: MainViewModel = viewModel(
                factory = ProviderFactory()
            )

            MainScreen()

        }

        composable(route = BottomBarScreens.Quran.route) {

            MainChapterScreen()

        }

        composable(route = BottomBarScreens.Hadith.route) {

            MainHadithScreen()

        }

        composable(route = BottomBarScreens.Supplications.route) {

            SupplicationScreen()

        }

    }

}