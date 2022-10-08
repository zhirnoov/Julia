package com.github.zhirnoov.julia.data.data_sources.card

import com.github.zhirnoov.julia.data.database.dao.CardDao
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import javax.inject.Inject

class CardLocalDataSource @Inject constructor(private val cardDao: CardDao) {

    suspend fun saveCard(card: CardEntity) {
        cardDao.saveCard(card)
    }

    suspend fun updateCardForNextRepeat(id: Int, next_repeat_dayOfYear: Int, stage_repeat: Int) {
        cardDao.updateCard(
            next_repeat_dayOfYear = next_repeat_dayOfYear, id = id, stage_repeat = stage_repeat
        )
    }

    fun getCards(collectionId : String) = cardDao.getCards(collectionId = collectionId)

    suspend fun deleteCard(card: CardEntity) {
        cardDao.deleteCard(card)
    }

    fun getCardsForRepeat(startDay: Int, endDay: Int) =
        cardDao.getCardsForRepeat(startDay = startDay, endDay = endDay)

}