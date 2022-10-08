package com.github.zhirnoov.julia.presentation.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val screens = listOf(
        BottomNavigationItem.Collections,
        BottomNavigationItem.Training,
        BottomNavigationItem.Settings
    )

        BottomNavigation(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 10.dp
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }

}

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(text = stringResource(id = screen.title))
        }, icon = {
            Icon(painter = painterResource(screen.icon), contentDescription = "navigation item")
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        selectedContentColor = Color(0xFF0288d1),
        unselectedContentColor = Color.Black,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState
                }
                launchSingleTop
                restoreState
            }
        })
}