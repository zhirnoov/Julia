package com.github.zhirnoov.julia.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert
    suspend fun addCollection(collection: CollectionEntity)

    @Query("SELECT * FROM collections")
    fun getAllCollections(): Flow<List<CollectionEntity>>

    @Delete
    suspend fun deleteCollection(collection : CollectionEntity)

    @Query("UPDATE collections set countCards =:countCards WHERE id =:id")
    suspend fun updateCollection(countCards : Int, id : String)
}