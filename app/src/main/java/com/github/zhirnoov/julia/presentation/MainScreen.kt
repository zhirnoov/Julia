package com.github.zhirnoov.julia.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.github.zhirnoov.julia.navigation.tabs.CollectionsTab
import com.github.zhirnoov.julia.navigation.tabs.SettingsTab
import com.github.zhirnoov.julia.navigation.tabs.TrainingTab
import com.github.zhirnoov.julia.ui.theme.JuliaAppTheme
import com.github.zhirnoov.julia.utlis.RememberWindowInfo
import com.github.zhirnoov.julia.utlis.WindowInfo

@Composable
fun MainScreen() {
    TabNavigator(tab = CollectionsTab) {
        Scaffold(content = {
            Column(modifier = Modifier.padding(it)) {
                CurrentTab()
            }
        },
            bottomBar = {
                BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                    TabNavigationItem(CollectionsTab)
                    TabNavigationItem(TrainingTab)
                    TabNavigationItem(SettingsTab)
                }


            })
    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigation = LocalTabNavigator.current

    val windowInfo = RememberWindowInfo()
    BottomNavigationItem(selected = tabNavigation.current == tab,
        label = {
            Text(
                text = tab.options.title, fontSize =
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded) 20.sp else 16.sp
            )
        },
        onClick = { tabNavigation.current = tab },
        selectedContentColor = Color(0xFF0288d1),
        unselectedContentColor = Color.LightGray,
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}