package com.example.islamicapp

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: Int
) {

    object Dashboard: BottomBarScreens(
        route = "dashboard",
        title = "Dashboard",
        icon = R.drawable.dashboard_selected
    )

    object Report: BottomBarScreens(
        route = "report",
        title = "Report",
        icon = R.drawable.report_selected
    )

}