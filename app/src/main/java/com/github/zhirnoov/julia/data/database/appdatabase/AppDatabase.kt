package com.github.zhirnoov.julia.data.database.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.zhirnoov.julia.data.database.dao.CardDao
import com.github.zhirnoov.julia.data.database.dao.CollectionDao
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity

@Database(
    version = 1,
    entities = [CardEntity::class, CollectionEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao() : CardDao
    abstract fun collectionDao() : CollectionDao
}