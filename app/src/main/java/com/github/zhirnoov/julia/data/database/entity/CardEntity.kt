package com.github.zhirnoov.julia.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cards")
data class CardEntity(
 @PrimaryKey
 val id: String = UUID.randomUUID().toString(),
 val collectionId : String,
 val MainSide: String,
 val BackSide : String,
 val stage_repeat : Int = 1,
 val next_repeat_dayOfYear : Int)