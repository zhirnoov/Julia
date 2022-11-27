package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class DeleteCardsByCollectionIdUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun execute(collectionId: String) =
        cardRepository.deleteCardsByCollectionId(collectionId = collectionId)
}