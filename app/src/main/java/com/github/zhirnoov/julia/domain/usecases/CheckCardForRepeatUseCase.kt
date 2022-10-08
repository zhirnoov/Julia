package com.github.zhirnoov.julia.domain.usecases

import com.github.zhirnoov.julia.domain.repository.CardRepository
import javax.inject.Inject

class CheckCardForRepeatUseCase @Inject constructor(private val cardRepository: CardRepository) {

    suspend fun execute(startDay: Int, endDay: Int) =
        cardRepository.getCardsForRepeat(
            startDay = startDay,
            endDay = endDay
        )
}