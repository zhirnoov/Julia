package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: CardRepository) {

    suspend fun execute(collectionId: String) = repository.getCards(collectionId = collectionId)
}