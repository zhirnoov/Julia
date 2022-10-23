package com.github.zhirnoov.julia.domain.usecases.collection

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class GetCardCountInCollectionUseCase @Inject constructor( private val cardRepository: CardRepository) {

    suspend fun execute(collectionId : String) = cardRepository.getCardCountInCollection(collectionId = collectionId)
}