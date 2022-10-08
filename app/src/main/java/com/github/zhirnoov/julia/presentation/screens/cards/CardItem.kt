package com.github.zhirnoov.julia.presentation.screens.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.zhirnoov.julia.data.database.entity.CardEntity

@Composable
fun CardItem(card: CardEntity, deleteCard: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = card.MainSide, style = MaterialTheme.typography.h5)
                }
                IconButton(onClick = deleteCard) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete card")
                }
            }

            Text(
                text = card.MainSide,
                style = MaterialTheme.typography.body1
            )

        }
    }
}