package com.github.zhirnoov.julia.presentation.screens.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.UIStateCards
import com.github.zhirnoov.julia.presentation.screens.LoadingProcess
import com.github.zhirnoov.julia.presentation.viewmodels.CardViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsScreen(
    viewModel: CardViewModel = viewModel(),
    collectionName: String?,
    collectionId: String?,
    cardsCount: Int?
) {


    LaunchedEffect(Unit) {
        viewModel.getAllCards(collectionId!!)
    }
    val state = viewModel.uiState.collectAsState()

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var cardMainSide by remember { mutableStateOf("") }
    var cardBackSide by remember { mutableStateOf("") }
    var cardsCount by rememberSaveable { mutableStateOf(cardsCount!!) }
    var buttonIsEnabled by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.currentOrThrow

    ModalBottomSheetLayout(sheetState = bottomState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetElevation = 4.dp,
        sheetContent = {
            buttonIsEnabled = !(cardMainSide.isBlank() || cardBackSide.isBlank())

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
                    textAlign = TextAlign.Center,
                    color = if (MaterialTheme.colors.isLight) Color.Black else Color.LightGray
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
                        textColor = Color.Black,
                        backgroundColor = Color.LightGray,
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
                        textColor = Color.Black,
                        backgroundColor = Color.LightGray,
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
                        val calendar = Calendar.getInstance()
                        viewModel.saveCard(
                            card = CardEntity(
                                collectionId = collectionId!!,
                                MainSide = cardMainSide,
                                BackSide = cardBackSide,
                                stage_repeat = 1,
                                next_repeat_dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
                            )
                        )
                        cardsCount += 1
                        viewModel.updateCountCardsInCollection(
                            id = collectionId,
                            countCards = cardsCount
                        )
                        scope.launch {
                            bottomState.hide()
                        }
                        cardMainSide = ""
                        cardBackSide = ""

                    }) { Text(text = "Сохранить", color = Color.White) }
            }
        }) {
        Scaffold(topBar = {
            TopAppBar(backgroundColor = Color(0xFF0277BD),
                title = { Text(text = collectionName!!, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navigator.pop() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "button back",
                            tint = Color.White
                        )
                    }
                })
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        bottomState.show()
                    }
                }, shape = RoundedCornerShape(50), backgroundColor = Color(0xFF03A9F4),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "fab button")
            }
        },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = false,
            content = { padding ->
                when (state.value) {
                    is UIStateCards.Loading -> LoadingProcess()
                    is UIStateCards.Error -> EmptyCardsMessage()
                    is UIStateCards.Success -> AllCards(
                        padding = padding,
                        cards = (state.value as UIStateCards.Success).cards,
                        deleteCard = { card ->
                            viewModel.deleteCard(card)
                            cardsCount -= 1
                            viewModel.updateCountCardsInCollection(
                                id = collectionId!!,
                                countCards = cardsCount
                            )
                        }
                    )
                }
            })
    }
}

@Composable
fun ShowDeleteCardDialogAlert(openDialog: MutableState<Boolean>, deleteCard: () -> Unit) {

    if (openDialog.value) {
        AlertDialog(onDismissRequest = { openDialog.value = false },
            title = {
                Text(
                    text = "Удаление карты",
                    color = if (MaterialTheme.colors.isLight) Color.Black else Color.White
                )
            }, text = {
                Text(
                    text = "Вы хотите удалить карту?",
                    color = if (MaterialTheme.colors.isLight) Color.DarkGray else Color.LightGray
                )
            },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                    deleteCard()
                }) {
                    Text(text = "Да")
                }
            }, dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(text = "Нет")
                }
            })
    }

}