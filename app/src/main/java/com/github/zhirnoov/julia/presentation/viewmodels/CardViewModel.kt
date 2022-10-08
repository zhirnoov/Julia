package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.UIStateCards
import com.github.zhirnoov.julia.domain.usecases.DeleteCardUseCase
import com.github.zhirnoov.julia.domain.usecases.GetCardsUseCase
import com.github.zhirnoov.julia.domain.usecases.SaveCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val saveCardUseCase: SaveCardUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val deleteCardUseCase: DeleteCardUseCase
) : ViewModel() {

    var cards = mutableStateListOf<CardEntity>()
    private val _uiState = MutableStateFlow<UIStateCards>(UIStateCards.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAllCards(collectionId : String) =
        viewModelScope.launch {
            getCardsUseCase.execute(collectionId = collectionId).collect {
                if (it.isEmpty()) {
                    _uiState.value = UIStateCards.Error
                } else {
                    cards.addAll(it)
                    _uiState.value = UIStateCards.Success(cards)
                }
            }
        }

        fun saveCard(card: CardEntity) = viewModelScope.launch {
            saveCardUseCase.execute(card)
            cards.add(card)
            if (cards.isNotEmpty()) {
                _uiState.value = UIStateCards.Success(cards)
            }
        }

        fun deleteCard(card: CardEntity) = viewModelScope.launch {
            cards.remove(card)
            deleteCardUseCase.execute(card)
            if (cards.isEmpty()) {
                _uiState.value = UIStateCards.Error
            }
        }
    }