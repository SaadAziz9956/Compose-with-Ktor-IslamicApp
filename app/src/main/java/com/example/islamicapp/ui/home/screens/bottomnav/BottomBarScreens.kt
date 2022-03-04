package com.example.islamicapp.ui.home.screens.bottomnav

import com.example.islamicapp.R


sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: Int
) {

    object Home: BottomBarScreens(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Quran: BottomBarScreens(
        route = "quran",
        title = "Quran",
        icon = R.drawable.ic_book
    )

    object Hadith: BottomBarScreens(
        route = "hadees",
        title = "Hadith",
        icon = R.drawable.ic_hadith
    )

    object Qibla: BottomBarScreens(
        route = "qibla",
        title = "Qibla",
        icon = R.drawable.ic_qibla
    )

}