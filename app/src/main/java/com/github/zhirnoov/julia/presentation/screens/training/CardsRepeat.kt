package com.github.zhirnoov.julia.presentation.screens.training

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.github.zhirnoov.julia.data.alarmManager.AlarmHelper
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.navigation.screens.CollectionsScreenNav
import com.github.zhirnoov.julia.navigation.tabs.CollectionsTab
import com.github.zhirnoov.julia.presentation.components.CardFace
import com.github.zhirnoov.julia.presentation.components.CardFlip
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel
import com.github.zhirnoov.julia.utlis.StageRepeat
import java.util.*

@Composable
fun CardsRepeat(cards: List<CardEntity>, viewModel: TrainingViewModel, padding: PaddingValues) {
    Log.d("JuliaTest", "OnCardsRepeat ${Date()}")

    val context = LocalContext.current
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

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = "${cardCount + 1}/${cards.size}", fontWeight = FontWeight.Bold)

                IndicatorProgress(progress = (cardCount.toFloat() / cards.size) / 1)

                CardFlip(cardFace = cardFace,
                    onClick = { cardFace = cardFace.next },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 10.dp),
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
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            AlarmHelper().setAlarm(
                                context, StageRepeat().changeDayForNextRepeat(
                                    currentDay = cards[cardCount].next_repeat_dayOfYear,
                                    repeatStage = cards[cardCount].stage_repeat
                                )
                            )
                            cardCount++
                        }) { Text(text = "Знаю") }

                    Button(modifier = Modifier.size(width = 120.dp, height = 50.dp),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            viewModel.updateCard(
                                next_repeatDays = cards[cardCount].next_repeat_dayOfYear + 1,
                                id = cards[cardCount].id,
                                stage_repeat = cards[cardCount].stage_repeat
                            )

                            AlarmHelper().setAlarm(
                                context,
                                cards[cardCount].next_repeat_dayOfYear + 1
                            )
                            cardCount++
                        }) { Text(text = "Не знаю") }
                }
            }

        } else {
            FinishTrainingMessage()
        }
    }
}

@Composable
fun FinishTrainingMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val navigationTab = LocalTabNavigator.current
        Text(text = "Поздравляем!")
        Text(modifier = Modifier.padding(top = 4.dp), text = "Вы завершили тренировку")
        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = {
                navigationTab.current = CollectionsTab
            }) {
            Text(text = "Вернуться")
        }
    }
}


@Composable
fun IndicatorProgress(progress: Float) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Spacer(
            modifier = Modifier
                .height(14.dp)
                .fillMaxWidth(progress)
                .clip(CircleShape)
                .background(Color(0xffFFA000))
        )
    }
}
