package com.github.zhirnoov.julia.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String
)