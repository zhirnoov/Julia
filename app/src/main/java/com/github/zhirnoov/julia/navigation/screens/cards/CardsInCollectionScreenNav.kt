package com.github.zhirnoov.julia.navigation.screens.cards

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.github.zhirnoov.julia.presentation.screens.cards.CardsScreen

data class CardsInCollectionScreenNav(val collectionName : String, val collectionId : String, val cardsCount : Int ) : Screen {

    @Composable
    override fun Content() {
        CardsScreen(collectionName = collectionName , collectionId =  collectionId, collectionCardsCount = cardsCount)
    }

}