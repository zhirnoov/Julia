package com.github.zhirnoov.julia.data.data_sources.collection

import com.github.zhirnoov.julia.data.database.dao.CollectionDao
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import javax.inject.Inject

class CollectionLocalDataSource @Inject constructor(private val collectionDao: CollectionDao) {

    suspend fun addCollection(collection : CollectionEntity) {
        collectionDao.addCollection(collection)
    }

    fun getAllCollection() = collectionDao.getAllCollections()

    suspend fun deleteCollection(collection: CollectionEntity) {
        collectionDao.deleteCollection(collection)
    }

}