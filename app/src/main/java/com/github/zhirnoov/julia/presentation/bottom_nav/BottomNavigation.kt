//package com.github.zhirnoov.julia.presentation.bottom_nav
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.github.zhirnoov.julia.R
//import com.github.zhirnoov.julia.navigation.*
//import com.github.zhirnoov.julia.navigation.graph.SettingDestination
//import com.github.zhirnoov.julia.navigation.graph.TrainingDestination
//import com.github.zhirnoov.julia.navigation.graph.collections.CollectionsDestination
//
//@SuppressLint("SuspiciousIndentation")
//@Composable
//fun BottomNavigation(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    navigate: (TopLevelDestination) -> Unit,
//) {
//
//    val screens = listOf(
//        BottomNavigationItem.Collections,
//        BottomNavigationItem.Training,
//        BottomNavigationItem.Settings
//    )
//
//    val destination: List<TopLevelDestination> = listOf(
//        TopLevelDestination(
//            route = CollectionsDestination.route,
//            destination = CollectionsDestination.destination,
//            selectedIcon = R.drawable.icon_cards,
//            text = R.string.title_collections
//        ),
//
//        TopLevelDestination(
//            route = TrainingDestination.route,
//            destination = TrainingDestination.destination,
//            selectedIcon = R.drawable.icon_training,
//            text = R.string.title_training
//        ),
//        TopLevelDestination(
//            route = SettingDestination.route,
//            destination = SettingDestination.destination,
//            selectedIcon = R.drawable.icon_settings,
//            text = R.string.title_setting
//        )
//    )
//
//
//}
//
//@Composable
//fun RowScope.AddItem(
//    destinations: List<TopLevelDestination>,
//    currentDestination: NavDestination?,
//    onNavigateToDestination: (TopLevelDestination) -> Unit
//) {
//
//    destinations.forEach { destination ->
//        val selected =
//            currentDestination?.hierarchy?.any { it.route == destination.route } == true
//        BottomNavigationItem(
//            label = {
//                Text(text = stringResource(id = destination.text))
//            }, icon = {
//                Icon(
//                    painter = painterResource(destination.selectedIcon),
//                    contentDescription = "navigation item"
//                )
//            },
//            selected = selected,
//            selectedContentColor = Color(0xFF0288d1),
//            unselectedContentColor = Color.LightGray,
//            onClick = { onNavigateToDestination(destination) }
//        )
//    }
//}