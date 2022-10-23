package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class EditCardUseCase @Inject constructor(private val cardRepository: CardRepository) {

    suspend fun execute(mainSide : String, backSide : String, id : String) {
        cardRepository.editCard(mainSide, backSide, id)
    }
}