package com.github.zhirnoov.julia.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.zhirnoov.julia.presentation.bottom_nav.BottomNavigationItem
import com.github.zhirnoov.julia.presentation.screens.cards.CardsScreen
import com.github.zhirnoov.julia.presentation.screens.collections.CollectionsScreen
import com.github.zhirnoov.julia.presentation.screens.training.settings.SettingsScreen
import com.github.zhirnoov.julia.presentation.screens.training.TrainingScreen
import com.github.zhirnoov.julia.presentation.viewmodels.CardViewModel
import com.github.zhirnoov.julia.presentation.viewmodels.CollectionViewModel
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = BottomNavigationItem.Collections.route
    ) {

        composable(route = BottomNavigationItem.Collections.route) {
            val viewModel = hiltViewModel<CollectionViewModel>()
            CollectionsScreen(
                viewModel = viewModel, navController = navController
            )
        }
        composable(route = BottomNavigationItem.Training.route) {
            val viewModel = hiltViewModel<TrainingViewModel>()
            TrainingScreen(viewModel = viewModel)
        }
        composable(route = BottomNavigationItem.Settings.route) { SettingsScreen() }

        composable(
            route = "collection/{collectionId}",
            arguments = listOf(
                navArgument("collectionId") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel = hiltViewModel<CardViewModel>()
            CardsScreen(
                viewModel = viewModel,
                collectionId = backStackEntry.arguments?.getString("collectionId")
            )
        }
    }
}