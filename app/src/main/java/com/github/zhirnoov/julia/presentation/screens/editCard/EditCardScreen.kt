package com.github.zhirnoov.julia.presentation.screens.editCard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.zhirnoov.julia.presentation.viewmodels.CardViewModel

@Composable
fun EditCardScreen(
    viewModel: CardViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    cardId: String,
    mainSide: String,
    backSide: String
) {

    var cardMainSide by remember { mutableStateOf(mainSide) }
    var cardBackSide by remember { mutableStateOf(backSide) }
    var buttonIsEnabled by remember { mutableStateOf(true) }
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color(0xFF0277BD),
            title = { Text(text = "Редактирование карточки", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navigator.pop() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "button back",
                        tint = Color.White
                    )
                }
            })
    }, content = { padding ->

        buttonIsEnabled = !(cardMainSide.isBlank() || cardBackSide.isBlank())

        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize(),
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
                .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                enabled = buttonIsEnabled,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF03A9F4),
                    disabledBackgroundColor = Color.LightGray
                ),
                onClick = {
                    viewModel.editCard(
                        mainSide = cardMainSide,
                        backSide = cardBackSide,
                        id = cardId
                    )
                    navigator.pop()
                }) {
                Text(
                    text = "Сохранить",
                    color = Color.White
                )
            }

        }
    })

}