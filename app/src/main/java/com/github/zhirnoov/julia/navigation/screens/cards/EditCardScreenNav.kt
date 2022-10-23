package com.github.zhirnoov.julia.navigation.screens.cards

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.github.zhirnoov.julia.presentation.screens.editCard.EditCardScreen

data class EditCardScreenNav(val cardId : String, val mainSide : String, val backSide : String) : Screen{

    @Composable
    override fun Content() {
        EditCardScreen(cardId = cardId, mainSide = mainSide, backSide = backSide)
    }

}