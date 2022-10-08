package com.github.zhirnoov.julia.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val collectionId : String,
    val MainSide: String,
    val BackSide : String,
    val stage_repeat : Int = 1,
    val next_repeat_dayOfYear : Int)