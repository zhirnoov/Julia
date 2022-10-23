package com.github.zhirnoov.julia.domain.repository

import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {

    suspend fun addCollection(collection : CollectionEntity)

    suspend fun getAllCollections() : Flow<List<CollectionEntity>>

    suspend fun deleteCollection(collection : CollectionEntity)

    suspend fun updateCollection(countCards : Int, id : String)

}