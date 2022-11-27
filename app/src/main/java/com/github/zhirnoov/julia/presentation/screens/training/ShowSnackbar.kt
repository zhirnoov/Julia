package com.github.zhirnoov.julia.presentation.screens.training

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

/*@Composable
fun ShowSnackBarTomorrowNextTraining(snackBarTomorrowVisible: MutableState<Boolean>) {

    if (snackBarTomorrowVisible.value) {
        Snackbar() {
            Text(text = "Следующее повторение завтра")
        }
    }
}*/

@Composable
fun ShowSnackBarNextDaysTraining(
    snackBarNextDaysVisible: MutableState<Boolean>,
    countDays: MutableState<Int>
) {
    if (snackBarNextDaysVisible.value) {
            Snackbar() {
                Text(text = "Следующее повторение ${setCountDaysTitle(countDays.value)}")
            }
        }
    }

fun setCountDaysTitle(countDays: Int): String {
    return when (countDays) {
        0, 1 -> "завтра"
        2, 3, 4 -> "через $countDays дня"
        else -> "через $countDays дней"
    }
}