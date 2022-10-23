package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.UIStateCards
import com.github.zhirnoov.julia.domain.UIStateTraining
import com.github.zhirnoov.julia.domain.usecases.CheckCardForRepeatUseCase
import com.github.zhirnoov.julia.domain.usecases.UpdateCardForNextRepeatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val checkCardForRepeatUseCase: CheckCardForRepeatUseCase,
    private val updateCardForNextRepeatUseCase: UpdateCardForNextRepeatUseCase
) : ViewModel() {
    var cardsForRepeat = mutableStateListOf<CardEntity>()
    private val _uiState = MutableStateFlow<UIStateTraining>(UIStateTraining.Loading)
    val uiState = _uiState.asStateFlow()


    fun checkCardsForRepeat(startDay: Int, endDay: Int) = viewModelScope.launch {
        checkCardForRepeatUseCase.execute(
            startDay = startDay, endDay = endDay
        ).collect {
            if (it.isEmpty()) {
                _uiState.value = UIStateTraining.Error
            } else {
                cardsForRepeat.clear()
                cardsForRepeat.addAll(it)
                _uiState.value = UIStateTraining.Success(cardsForRepeat)
            }

        }
    }

    fun updateCard(next_repeatDays: Int, id: String, stage_repeat: Int) = viewModelScope.launch {
        updateCardForNextRepeatUseCase.execute(
            next_repeatDays = next_repeatDays, id = id, stage_repeat = stage_repeat
        )
    }

}