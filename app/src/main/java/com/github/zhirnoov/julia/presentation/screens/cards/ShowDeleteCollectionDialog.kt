package com.github.zhirnoov.julia.presentation.screens.cards

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.Job

@Composable
fun ShowDeleteCollectionDialog(
    openDialog: MutableState<Boolean>,
    collectionName: String,
    deleteCollection: () -> Unit
) {

    if (openDialog.value) {
        AlertDialog(onDismissRequest = { openDialog.value = false }, title = {
            Text(
                text = "Удаление коллекции",
                color = if (MaterialTheme.colors.isLight) Color.Black else Color.White
            )
        }, text = {
            Text(
                text = "Вы хотите удалить коллекцию $collectionName?",
                color = if (MaterialTheme.colors.isLight) Color.DarkGray else Color.LightGray
            )
        }, confirmButton = {
            Button(onClick = {
                deleteCollection()
                openDialog.value = false
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