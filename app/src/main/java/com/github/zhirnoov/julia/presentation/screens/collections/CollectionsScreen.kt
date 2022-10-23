package com.github.zhirnoov.julia.presentation.screens.collections


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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.UIState
import com.github.zhirnoov.julia.presentation.screens.LoadingProcess
import com.github.zhirnoov.julia.presentation.viewmodels.CollectionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionsScreen(
    viewModel: CollectionViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getAllCollections()
    }

    val state = viewModel.uiState.collectAsState().value
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(sheetState = bottomState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetElevation = 4.dp,
        sheetContent = {
            var collectionName by remember { mutableStateOf("") }
            var buttonIsEnabled by remember { mutableStateOf(false) }

            buttonIsEnabled = collectionName.isNotBlank()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = "Новая коллекция",
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
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Введите название для коллекции",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.W400,
                    color = Color.LightGray
                )


                TextField(modifier = Modifier
                    .padding(top = 20.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),

                    value = collectionName,
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Название коллекции") },
                    onValueChange = { collectionName = it })

                Button(modifier = Modifier
                    .padding(top = 10.dp, bottom = 16.dp)
                    .height(60.dp)
                    .fillMaxWidth(), shape = RoundedCornerShape(10.dp),
                    enabled = buttonIsEnabled,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF03A9F4),
                        disabledBackgroundColor = Color.LightGray
                    ),
                    onClick = {
                        scope.launch {
                            bottomState.hide()
                        }
                        val collection = CollectionEntity(name = collectionName)
                        viewModel.addCollection(collection)
                        collectionName = ""
                    }) { Text(text = "Сохранить", color = Color.White) }

            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color(0xFF0277BD),
                    title = { Text(text = "Коллекции", color = Color.White) })
            },
            floatingActionButton = {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    when (state) {
                        is UIState.Loading -> LoadingProcess()
                        is UIState.Error -> EmptyCollectionsMessage()
                        is UIState.Success -> AllCollections(
                            padding = padding,
                            collections = state.collections
                        )
                    }
                }
            })
    }
}