package com.example.islamicapp.ui.home.screens.tablayout

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.islamicapp.R
import com.example.islamicapp.ui.theme.AppBackground
import com.example.islamicapp.ui.theme.LightGrey


enum class TabPage(val icon: Int) {
    Book(R.drawable.ic_book),
    Hadith(R.drawable.ic_hadith),
    Names(R.drawable.ic_allah),
    Dua(R.drawable.ic_dua)
}

@Composable
fun TabHome(selectedTabIndex: Int, onSelectedTab: (TabPage) -> Unit) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = AppBackground,
        contentColor = AppBackground,
        divider = {
            Divider(
                color = AppBackground
            )
        }
    ) {
        TabPage.values().forEachIndexed { index, tabPages ->
            Tab(
                selected = index == selectedTabIndex, onClick = { onSelectedTab(tabPages) },
                icon = {
                    Icon(
                        painter = painterResource(id = tabPages.icon),
                        contentDescription = ""
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = LightGrey
            )
        }
    }
}