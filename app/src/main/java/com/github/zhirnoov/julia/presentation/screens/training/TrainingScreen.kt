package com.github.zhirnoov.julia.presentation.screens.training

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zhirnoov.julia.domain.UIStateTraining
import com.github.zhirnoov.julia.presentation.screens.LoadingProcess
import com.github.zhirnoov.julia.presentation.viewmodels.TrainingViewModel
import java.util.*

@Composable
fun TrainingScreen(
    modifier: Modifier = Modifier, viewModel: TrainingViewModel = viewModel()
) {
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_YEAR)
    viewModel.checkCardsForRepeat(startDay = day - 3, endDay = day)
    val state = viewModel.uiState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color(0xFF0277BD),
            title = { Text(text = "Ваша тренировка", color = Color.White) })
    }, content = { padding ->
            when(state.value) {
                is UIStateTraining.Loading -> LoadingProcess()
                is UIStateTraining.Error -> TrainingEmptyMessage()
                is UIStateTraining.Success -> CardsRepeat(cards = (state.value as UIStateTraining.Success).cards, viewModel = viewModel, padding)

            }
        })
        }

@Composable
fun TrainingEmptyMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .padding(12.dp)
                .size(width = 160.dp, height = 155.dp),
            painter = painterResource(com.github.zhirnoov.julia.R.drawable.cards_empty_logo),
            contentDescription = "image empty cards for training",
            alignment = Alignment.Center
        )
        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = stringResource(com.github.zhirnoov.julia.R.string.emptyTraining_text),
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )
    }
}
