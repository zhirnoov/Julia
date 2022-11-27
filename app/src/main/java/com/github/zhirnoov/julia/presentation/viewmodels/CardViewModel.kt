package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.UIStateCards
import com.github.zhirnoov.julia.domain.usecases.*
import com.github.zhirnoov.julia.domain.usecases.collection.DeleteCollectionUseCase
import com.github.zhirnoov.julia.domain.usecases.collection.UpdateCountCardInCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val saveCardUseCase: SaveCardUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val updateCountCardInCollectionUseCase: UpdateCountCardInCollectionUseCase,
    private val editCardUseCase: EditCardUseCase,
    private val deleteCardsByCollectionIdUseCase: DeleteCardsByCollectionIdUseCase,
) : ViewModel() {

    var cards = mutableStateListOf<CardEntity>()
    private val _uiState = MutableStateFlow<UIStateCards>(UIStateCards.Loading)
    val uiState = _uiState.asStateFlow()

      fun getAllCards(collectionId: String) =
        viewModelScope.launch {
            getCardsUseCase.execute(collectionId = collectionId).collect {
                if (it.isEmpty()) {
                    _uiState.value = UIStateCards.Error
                } else {
                    cards.clear()
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

    fun updateCountCardsInCollection(countCards: Int, id: String) {
        viewModelScope.launch {
            updateCountCardInCollectionUseCase.execute(countCards = countCards, id = id)
        }
    }

    fun editCard(mainSide : String, backSide : String, id : String) {
        viewModelScope.launch {
            editCardUseCase.execute(mainSide = mainSide, backSide = backSide, id = id)
        }
    }

    fun deleteCardsByCollectionId(collectionId: String) {
        viewModelScope.launch {
            deleteCardsByCollectionIdUseCase.execute(collectionId = collectionId)
            cards.clear()
        }
    }
}