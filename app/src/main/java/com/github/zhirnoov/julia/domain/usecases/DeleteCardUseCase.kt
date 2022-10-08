package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(private val repository: CardRepository) {

    suspend fun execute(card: CardEntity) = repository.deleteCard(card)
}