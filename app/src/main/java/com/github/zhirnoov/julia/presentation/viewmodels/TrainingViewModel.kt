package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.UIState
import com.github.zhirnoov.julia.domain.usecases.CheckCardForRepeatUseCase
import com.github.zhirnoov.julia.domain.usecases.UpdateCardForNextRepeatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val checkCardForRepeatUseCase: CheckCardForRepeatUseCase,
    private val updateCardForNextRepeatUseCase: UpdateCardForNextRepeatUseCase
) : ViewModel() {
    var cardsForRepeat = mutableStateOf<List<CardEntity>>(emptyList())


    fun checkCardsForRepeat(startDay: Int, endDay: Int) = viewModelScope.launch {
        checkCardForRepeatUseCase.execute(
            startDay = startDay, endDay = endDay
        ).collect {
            cardsForRepeat.value = it
        }
    }

    fun updateCard(next_repeatDays: Int, id: Int, stage_repeat: Int) = viewModelScope.launch {
        updateCardForNextRepeatUseCase.execute(
            next_repeatDays = next_repeatDays, id = id, stage_repeat = stage_repeat
        )
    }

}