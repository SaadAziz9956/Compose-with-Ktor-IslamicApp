package com.example.islamicapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.islamicapp.ui.theme.MidGrey
import com.example.islamicapp.ui.theme.Yellow

@Composable
fun BottomNavScreen() {

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {

        BottomNavGraph(navController = navController)

    }

}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(

        BottomBarScreens.Dashboard,

        BottomBarScreens.Report,

        )

    val navBacStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBacStackEntry?.destination

    BottomNavigation {
        screens.forEach { screens ->

            AddItems(
                screen = screens,
                currentDestination = currentDestination,
                navController = navController
            )

        }
    }

}


@Composable
fun RowScope.AddItems(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController?
) {

    BottomNavigationItem(
        modifier = Modifier.background(color = Color.White),
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(20.dp),
            )
        },
        selectedContentColor = Yellow,
        unselectedContentColor = MidGrey,
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController?.navigate(screen.route)
        }
    )

}