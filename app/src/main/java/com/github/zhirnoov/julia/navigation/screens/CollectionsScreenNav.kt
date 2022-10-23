package com.github.zhirnoov.julia.navigation.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.github.zhirnoov.julia.presentation.screens.collections.CollectionsScreen

class CollectionsScreenNav : Screen {

    @Composable
    override fun Content() {
        CollectionsScreen()
    }
}