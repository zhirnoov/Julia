package com.github.zhirnoov.julia.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.github.zhirnoov.julia.navigation.tabs.CollectionsTab
import com.github.zhirnoov.julia.navigation.tabs.SettingsTab
import com.github.zhirnoov.julia.navigation.tabs.TrainingTab
import com.github.zhirnoov.julia.presentation.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            TabNavigator(tab = CollectionsTab) {
                Scaffold(content = {
                    Column(modifier = Modifier.padding(it)) {
                        CurrentTab()
                    }
                },
                    bottomBar = {
                        BottomNavigation(backgroundColor = Color.White) {
                            TabNavigationItem(CollectionsTab)
                            TabNavigationItem(TrainingTab)
                            TabNavigationItem(SettingsTab)
                        }
                    })
            }
        }
    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigation = LocalTabNavigator.current

    BottomNavigationItem(selected = tabNavigation.current == tab,
        label = { Text(text = tab.options.title) },
        onClick = { tabNavigation.current = tab },
        selectedContentColor = Color(0xFF0288d1),
        unselectedContentColor = Color.LightGray,
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}