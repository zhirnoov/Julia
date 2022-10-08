package com.github.zhirnoov.julia.presentation.screens.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel
import java.util.*

@Composable
fun TrainingScreen(
    modifier: Modifier = Modifier, viewModel: TrainingViewModel = viewModel()
) {
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_YEAR)
    viewModel.checkCardsForRepeat(startDay = day - 3, endDay = day)
    val cardsForRepeat = viewModel.cardsForRepeat.value

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (cardsForRepeat.isNotEmpty()) {
            CardsRepeat(cards = cardsForRepeat, viewModel = viewModel)
        } else {
            Text(text = "Нет карточек для повторения")
        }
    }
}