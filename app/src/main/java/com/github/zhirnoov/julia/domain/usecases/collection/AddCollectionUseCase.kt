package com.github.zhirnoov.julia.domain.usecases.collection

import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.repository.CollectionRepository
import javax.inject.Inject

class AddCollectionUseCase @Inject constructor(private val collectionRepository: CollectionRepository){


    suspend fun execute(collection : CollectionEntity) = collectionRepository.addCollection(collection)
}