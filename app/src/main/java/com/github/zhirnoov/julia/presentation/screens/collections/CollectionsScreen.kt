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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.UIState
import com.github.zhirnoov.julia.presentation.screens.LoadingProcess
import com.github.zhirnoov.julia.presentation.viewmodels.CollectionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionsScreen(
    viewModel: CollectionViewModel = viewModel(), navController: NavHostController
) {

    val state = viewModel.uiState.collectAsState().value
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(sheetState = bottomState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetElevation = 4.dp,
        sheetContent = {
            var collectionName by remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 6.dp),
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
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(modifier = Modifier
                    .height(60.dp)
                    .padding(top = 6.dp)
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
                    .fillMaxWidth(), shape = RoundedCornerShape(10.dp), onClick = {
                    scope.launch {
                        bottomState.hide()
                    }
                    val collection = CollectionEntity(name = collectionName)
                    viewModel.addCollection(collection)
                    // collectionName = ""
                }) { Text(text = "Сохранить") }

            }
        }) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.allCollectionsScreemTitle)) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            bottomState.show()
                        }
                    }, shape = RoundedCornerShape(50), backgroundColor = Color(0xFFFFeb3b)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "fab button")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = false,
            content = { padding ->
                when (state) {
                    is UIState.Loading -> LoadingProcess()
                    is UIState.Error -> EmptyCollectionsMessage()
                    is UIState.Success -> AllCollections(padding = padding,
                        collections = state.collections,
                        onCardsInCollection = { collection ->
                            navController.navigate("collection/${collection.id}")
                        })
                }
            })
    }
}