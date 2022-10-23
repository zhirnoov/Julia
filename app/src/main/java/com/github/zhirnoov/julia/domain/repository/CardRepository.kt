package com.github.zhirnoov.julia.domain.repository

import com.github.zhirnoov.julia.data.database.entity.CardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun saveCard(card : CardEntity)

    suspend fun getCards(collectionId : String) : Flow<List<CardEntity>>

    suspend fun deleteCard(card: CardEntity)

    suspend fun getCardsForRepeat(startDay : Int, endDay : Int) : Flow<List<CardEntity>>

    suspend fun updateCard(next_repeatDays: Int, id: String, stage_repeat: Int)

    suspend fun editCard(mainSide : String, backSide : String, id : String)

    suspend fun getCardCountInCollection(collectionId : String) : Int
}