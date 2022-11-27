package com.github.zhirnoov.julia.presentation.screens.training

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.data.alarmManager.AlarmHelper
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.presentation.components.CardFace
import com.github.zhirnoov.julia.presentation.components.CardFlip
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel
import com.github.zhirnoov.julia.utlis.RememberWindowInfo
import com.github.zhirnoov.julia.utlis.StageRepeat
import com.github.zhirnoov.julia.utlis.WindowInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CardsRepeat(cards: List<CardEntity>, viewModel: TrainingViewModel, padding: PaddingValues) {

    val windowInfo = RememberWindowInfo()

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var cardCount by remember { mutableStateOf(0) }
        val snackBarNextDaysVisible = remember { mutableStateOf(false) }
        val countDaysForNextTraining = remember { mutableStateOf(0) }
        val scope = rememberCoroutineScope()

        if (cards.size > cardCount) {
            var cardFace by remember {
                mutableStateOf(CardFace.FrontSide)
            }

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "${cardCount + 1}/${cards.size}",
                fontWeight = FontWeight.W500
            )
            IndicatorProgress(
                modifier = Modifier.padding(top = 10.dp),
                progress = (cardCount.toFloat() / cards.size.toFloat())
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CardFlip(cardFace = cardFace,
                    onClick = { cardFace = cardFace.next },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 300.dp else 400.dp)
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
                    ShowSnackBarNextDaysTraining(
                        snackBarNextDaysVisible = snackBarNextDaysVisible,
                        countDays = countDaysForNextTraining
                    )

                    Button(modifier = Modifier.size(width = 120.dp, height = 50.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF03A9F4),
                        ),
                        onClick = {
                            countDaysForNextTraining.value = StageRepeat().changeDayForNextRepeat(
                                currentDay = cards[cardCount].next_repeat_dayOfYear,
                                repeatStage = cards[cardCount].stage_repeat
                            ) - cards[cardCount].next_repeat_dayOfYear
                            snackBarNextDaysVisible.value = true
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
                            scope.launch {
                                cardFace = CardFace.FrontSide
                                if (cardFace == CardFace.FrontSide) {
                                    delay(400)
                                    snackBarNextDaysVisible.value = false
                                    cardCount++
                                }
                            }

                        }) { Text(text = "Знаю", color = Color.White) }

                    Button(modifier = Modifier.size(width = 120.dp, height = 50.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFD32F2F),
                        ),
                        onClick = {
                            snackBarNextDaysVisible.value = true
                            viewModel.updateCard(
                                next_repeatDays = cards[cardCount].next_repeat_dayOfYear + 1,
                                id = cards[cardCount].id,
                                stage_repeat = cards[cardCount].stage_repeat
                            )

                            AlarmHelper().setAlarm(
                                context,
                                cards[cardCount].next_repeat_dayOfYear + 1
                            )
                            scope.launch {
                                cardFace = CardFace.FrontSide
                                if (cardFace == CardFace.FrontSide) {
                                    delay(400)
                                    snackBarNextDaysVisible.value = false
                                    cardCount++
                                }
                            }
                        }) { Text(text = "Не знаю", color = Color.White) }
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
        val windowInfo = RememberWindowInfo()
        Image(
            modifier = Modifier.size(if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 200.dp else 250.dp),
            painter = painterResource(R.drawable.finish_training_img),
            contentDescription = "finish training image"
        )
        Text(
            modifier = Modifier.padding(top = 40.dp), text = "Поздравляем!",
            fontWeight = FontWeight.W500,
            fontSize = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 20.sp else 24.sp
        )
        Text(
            modifier = Modifier.padding(top = 6.dp), text = "Вы завершили тренировку",
            fontWeight = FontWeight.W300,
            fontSize = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 14.sp else 18.sp
        )
    }
}


@Composable
fun IndicatorProgress(modifier: Modifier, progress: Float) {

    Column(modifier = modifier) {
        Box(
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

}
