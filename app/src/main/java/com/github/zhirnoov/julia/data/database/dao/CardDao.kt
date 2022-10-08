package com.github.zhirnoov.julia.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = IGNORE)
    suspend fun saveCard(cardEntity: CardEntity)

    @Query("UPDATE cards SET next_repeat_dayOfYear =:next_repeat_dayOfYear, stage_repeat =:stage_repeat WHERE id =:id")
    suspend fun updateCard(next_repeat_dayOfYear: Int, id: Int, stage_repeat: Int)

    @Query("SELECT * FROM cards WHERE collectionId =:collectionId")
    fun getCards(collectionId : String): Flow<List<CardEntity>>

    @Delete
    suspend fun deleteCard(cardEntity: CardEntity)

    @Query("SELECT * FROM cards WHERE next_repeat_dayOfYear BETWEEN :startDay AND :endDay")
    fun getCardsForRepeat(startDay: Int, endDay : Int): Flow<List<CardEntity>>

}
