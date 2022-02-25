package com.example.islamicapp.ui.main.screens

import com.example.islamicapp.R


sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: Int
) {

    object Dashboard: BottomBarScreens(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Report: BottomBarScreens(
        route = "quran",
        title = "Quran",
        icon = R.drawable.ic_book
    )

}