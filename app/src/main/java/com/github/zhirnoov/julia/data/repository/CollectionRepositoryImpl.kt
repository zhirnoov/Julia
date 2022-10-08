package com.github.zhirnoov.julia.data.repository

import com.github.zhirnoov.julia.data.data_sources.collection.CollectionLocalDataSource
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.repository.CollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CollectionRepositoryImpl(private val collectionLocalDataSource: CollectionLocalDataSource) :
    CollectionRepository {

    override suspend fun addCollection(collection: CollectionEntity) = withContext(Dispatchers.IO) {
        collectionLocalDataSource.addCollection(collection)
    }

    override suspend fun getAllCollections(): Flow<List<CollectionEntity>> =
        withContext(Dispatchers.IO) {
            collectionLocalDataSource.getAllCollection()
        }

    override suspend fun deleteCollection(collection: CollectionEntity) {
        withContext(Dispatchers.IO) {
            collectionLocalDataSource.deleteCollection(collection)
        }
    }
}