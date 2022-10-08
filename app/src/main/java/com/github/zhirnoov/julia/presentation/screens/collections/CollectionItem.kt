package com.github.zhirnoov.julia.presentation.screens.collections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity


@Composable
fun CollectionItem(
    collection: CollectionEntity, onCardsInCollection: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = collection.name, style = MaterialTheme.typography.h6, color = Color.Black
        )
        Icon(
            modifier = Modifier.clickable(onClick = onCardsInCollection),
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "delete card",
            tint = Color.Gray
        )

    }
    Divider(modifier = Modifier.padding(top = 6.dp), color = Color.Gray, thickness = 0.5.dp)

}
