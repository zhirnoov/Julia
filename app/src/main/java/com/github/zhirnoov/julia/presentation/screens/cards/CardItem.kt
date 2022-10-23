package com.github.zhirnoov.julia.presentation.screens.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.navigation.screens.cards.EditCardScreenNav

@Composable
fun CardItem(card: CardEntity, deleteCard: () -> Unit) {

    var badgeCount by remember { mutableStateOf(0) }
    val navigator = LocalNavigator.currentOrThrow

    Card(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                text = card.MainSide,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow) {
                        val lineEndIndex = textLayoutResult.getLineEnd(
                            lineIndex = 0,
                            visibleEnd = true
                        )
                        badgeCount = card.MainSide
                            .substring(lineEndIndex)
                            .count { it == ',' }
                    }
                },
            )
            IconButton(onClick = { navigator.push(EditCardScreenNav(
                cardId = card.id,
                mainSide = card.MainSide,
                backSide = card.BackSide
            )) } ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit card")
            }
            IconButton(onClick = deleteCard ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete card")
            }
        }
    }
}