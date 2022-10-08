package com.github.zhirnoov.julia.presentation.screens.training

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.unit.dp
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.presentation.components.CardFace
import com.github.zhirnoov.julia.presentation.components.CardFlip
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel
import com.github.zhirnoov.julia.utlis.StageRepeat

@Composable
fun CardsRepeat(cards: List<CardEntity>, viewModel: TrainingViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var cardCount by remember { mutableStateOf(0) }

        if (cards.size > cardCount) {
            var cardFace by remember {
                mutableStateOf(CardFace.FrontSide)
            }
            CardFlip(cardFace = cardFace,
                onClick = { cardFace = cardFace.next },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                front = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = cards[cardCount].MainSide)
                    }
                },
                back = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Magenta),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = cards[cardCount].BackSide, color = Color.White)
                    }
                })

            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(modifier = Modifier.size(width = 120.dp, height = 50.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        viewModel.updateCard(
                            next_repeatDays = StageRepeat().changeDayForNextRepeat(
                                currentDay = cards[cardCount].next_repeat_dayOfYear,
                                repeatStage = cards[cardCount].stage_repeat
                            ),
                            id = cards[cardCount].id,
                            stage_repeat = cards[cardCount].stage_repeat + 1
                        )
                        cardCount++
                    }) { Text(text = "Знаю") }
            }

        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Поздравляем! Вы завершили тренировку карточек")
            }
        }

    }
}