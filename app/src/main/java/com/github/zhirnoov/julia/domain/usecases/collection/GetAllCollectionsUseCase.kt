package com.github.zhirnoov.julia.domain.usecases.collection

import com.github.zhirnoov.julia.domain.repository.CollectionRepository
import javax.inject.Inject

class GetAllCollectionsUseCase @Inject constructor(private val collectionRepository: CollectionRepository) {

    suspend fun execute() = collectionRepository.getAllCollections()

}