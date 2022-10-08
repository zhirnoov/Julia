package com.github.zhirnoov.julia.presentation.screens.cards

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.zhirnoov.julia.data.database.entity.CardEntity

@Composable
fun AllCards(
    cards: SnapshotStateList<CardEntity>,
    deleteCard: (card: CardEntity) -> Unit,
    padding: PaddingValues)
{
    LazyColumn() {
        items(items = cards) { card ->
            CardItem(card = card, deleteCard = {
                deleteCard(card)
            })
        }
    }

}