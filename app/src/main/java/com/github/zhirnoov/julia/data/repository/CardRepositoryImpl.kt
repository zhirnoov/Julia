package com.github.zhirnoov.julia.data.repository

import com.github.zhirnoov.julia.data.data_sources.card.CardLocalDataSource
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepositoryImpl(private val cardLocalDataSource: CardLocalDataSource) : CardRepository {

    override suspend fun saveCard(card: CardEntity) =
        withContext(Dispatchers.IO) { cardLocalDataSource.saveCard(card) }

    override suspend fun getCards(collectionId: String) =
        withContext(Dispatchers.IO) { cardLocalDataSource.getCards(collectionId = collectionId) }

    override suspend fun deleteCard(card: CardEntity) {
        withContext(Dispatchers.IO) {
            cardLocalDataSource.deleteCard(card)
        }
    }

    override suspend fun getCardsForRepeat(startDay: Int, endDay: Int) =
        withContext(Dispatchers.IO) {
            cardLocalDataSource.getCardsForRepeat(
                startDay = startDay, endDay = endDay
            )
        }

    override suspend fun updateCard(next_repeatDays: Int, id: String, stage_repeat: Int) =
        withContext(Dispatchers.IO) {
            cardLocalDataSource.updateCardForNextRepeat(
                id = id,
                next_repeat_dayOfYear = next_repeatDays,
                stage_repeat = stage_repeat
            )
        }

    override suspend fun editCard(mainSide: String, backSide: String, id: String) {
        withContext(Dispatchers.IO) {
            cardLocalDataSource.editCard(mainSide, backSide, id)
        }
    }

    override suspend fun getCardCountInCollection(collectionId: String): Int =
        withContext(Dispatchers.IO) {
            cardLocalDataSource.getCardsCountInCollection(collectionId = collectionId)
        }

    override suspend fun deleteCardsByCollectionId(collectionId: String) =
        withContext(Dispatchers.IO) {
            cardLocalDataSource.deleteCardByCollectionId(collectionId = collectionId)
        }
    }