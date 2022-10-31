package com.github.zhirnoov.julia.presentation.screens.collections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.navigation.screens.cards.CardsInCollectionScreenNav


@Composable
fun CollectionItem(
    collection: CollectionEntity
) {

    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = Modifier
            .clickable(onClick = { navigator.push(CardsInCollectionScreenNav(
                collectionName = collection.name,
                collectionId = collection.id,
                cardsCount = collection.countCards
            ))})
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF039be5)), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_collection),
                contentDescription = "icon collection",
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = collection.name, fontSize = 20.sp, fontWeight = FontWeight.W500,
                textAlign = TextAlign.Start
            )
            Text(
                text = setCountCardsTitle(countCards = collection.countCards),
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "delete card",
            tint = Color.Gray
        )

    }

}

fun setCountCardsTitle(countCards: Int): String {
    return when (countCards) {
        1, 21, 31, 41 -> "$countCards карта"
        2, 3, 4, 22, 23, 24 -> "$countCards карты"
        else -> "$countCards карт"
    }
}
