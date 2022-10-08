package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class SaveCardUseCase @Inject constructor(private val cardRepository: CardRepository) {

    suspend fun execute(card: CardEntity) = cardRepository.saveCard(card)
}