package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class UpdateCardForNextRepeatUseCase @Inject constructor(private val cardRepository: CardRepository) {

    suspend fun execute(next_repeatDays: Int, id: String, stage_repeat: Int) {
        cardRepository.updateCard(next_repeatDays = next_repeatDays, id = id, stage_repeat = stage_repeat)
    }
}