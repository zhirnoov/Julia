package com.github.zhirnoov.julia.presentation.screens.collections

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity

@Composable
fun AllCollections(
    padding: PaddingValues,
    collections: List<CollectionEntity>,
    onCardsInCollection : (collection : CollectionEntity) -> Unit
) {

    LazyColumn() {
        items(items = collections) { collection ->
            CollectionItem(collection = collection, onCardsInCollection = {
                onCardsInCollection(collection)
            })
        }
    }
}