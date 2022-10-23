package com.github.zhirnoov.julia.presentation.screens.collections

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity

@Composable
fun AllCollections(
    padding: PaddingValues,
    collections: List<CollectionEntity>,
) {

    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
        items(items = collections) { collection ->
            CollectionItem(collection = collection)
        }
    }
}