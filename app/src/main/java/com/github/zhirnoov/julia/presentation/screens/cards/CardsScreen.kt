package com.github.zhirnoov.julia.presentation.screens.cards

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.UIStateCards
import com.github.zhirnoov.julia.presentation.screens.LoadingProcess
import com.github.zhirnoov.julia.presentation.viewmodels.CardViewModel
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsScreen(viewModel: CardViewModel = viewModel(), collectionId: String?) {

    Log.d("JuliaTesting", "collectionID = $collectionId")

    LaunchedEffect(Unit) {
        viewModel.getAllCards(collectionId = collectionId!!)
    }

    Log.d("JuliaTesting", "collectionID = $collectionId")

    val state = viewModel.uiState.collectAsState().value
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(sheetState = bottomState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetElevation = 4.dp,
        sheetContent = {

            var cardMainSide by remember { mutableStateOf("") }
            var cardBackSide by remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Новая карта",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Icon(
                    modifier = Modifier.clickable(onClick = { scope.launch { bottomState.hide() } }),
                    imageVector = Icons.Default.Close,
                    contentDescription = "close button",
                    tint = Color.DarkGray
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(modifier = Modifier
                    .height(100.dp)
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),

                    value = cardMainSide,
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Информация для запоминания") },
                    onValueChange = { cardMainSide = it })

                TextField(modifier = Modifier
                    .height(100.dp)
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),

                    value = cardBackSide,
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Обратная сторона карты") },
                    onValueChange = { cardBackSide = it })

                Button(modifier = Modifier
                    .padding(top = 10.dp, bottom = 16.dp)
                    .height(60.dp)
                    .fillMaxWidth(), shape = RoundedCornerShape(10.dp), onClick = {
                    val calendar = Calendar.getInstance()
                    viewModel.saveCard(
                        card = CardEntity(
                            id = 0,
                            collectionId = collectionId!!,
                            MainSide = cardMainSide,
                            BackSide = cardBackSide,
                            stage_repeat = 1,
                            next_repeat_dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
                        )
                    )
                    scope.launch {
                        bottomState.hide()
                    }
                    cardBackSide = ""
                    cardBackSide = ""

                }) { Text(text = "Сохранить") }
            }
        }) {
        Scaffold(floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        bottomState.show()
                    }
                }, shape = RoundedCornerShape(50), backgroundColor = Color(0xFFFFeb3b)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "fab button")
            }
        }, content = { padding ->
            when (state) {
                is UIStateCards.Loading -> LoadingProcess()
                is UIStateCards.Error -> EmptyCardsMessage()
                is UIStateCards.Success -> AllCards(padding = padding,
                    cards = state.cards,
                    deleteCard = { card ->
                        viewModel.deleteCard(card)
                    })
            }
        })
    }
}