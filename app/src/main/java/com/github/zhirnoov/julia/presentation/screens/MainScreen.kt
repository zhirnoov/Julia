package com.github.zhirnoov.julia.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.zhirnoov.julia.navigation.Navigation
import com.github.zhirnoov.julia.presentation.bottom_nav.BottomNavigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }, content = { padding ->
            Box(Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        })
}
