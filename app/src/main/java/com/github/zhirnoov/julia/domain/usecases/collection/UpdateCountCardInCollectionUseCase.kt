package com.github.zhirnoov.julia.domain.usecases.collection

import com.github.zhirnoov.julia.domain.repository.CollectionRepository
import javax.inject.Inject

class UpdateCountCardInCollectionUseCase @Inject constructor(private val collectionRepository: CollectionRepository) {

    suspend fun execute(countCards: Int, id: String) {
        collectionRepository.updateCollection(countCards = countCards, id = id)
    }
}