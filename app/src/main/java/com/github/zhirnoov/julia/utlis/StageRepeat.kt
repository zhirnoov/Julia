package com.github.zhirnoov.julia.utlis

import java.util.Calendar

class StageRepeat {

    fun changeDayForNextRepeat(currentDay: Int, repeatStage: Int): Int {
        val day = calcDays(currentDay = currentDay, repeatStage = repeatStage)
        val countDaysOfYear = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)

        return if (day > countDaysOfYear) {
            day - countDaysOfYear
        } else {
            day
        }
    }

    companion object {
        const val DAYS_FOR_SECOND_REPEAT_STAGE = 1
        const val DAYS_FOR_THREE_REPEAT_STAGE = 5
        const val DAYS_FOR_FOUR_REPEAT_STAGE = 14
        const val DAYS_FOR_FIVE_REPEAT_STAGE = 30
        const val DAYS_FOR_SIX_REPEAT_STAGE = 60
    }

    private fun calcDays(currentDay: Int, repeatStage: Int): Int {
        return when (repeatStage) {
            1 -> currentDay + DAYS_FOR_SECOND_REPEAT_STAGE
            2 -> currentDay + DAYS_FOR_THREE_REPEAT_STAGE
            3 -> currentDay + DAYS_FOR_FOUR_REPEAT_STAGE
            4 -> currentDay + DAYS_FOR_FIVE_REPEAT_STAGE
            5 -> currentDay + DAYS_FOR_SIX_REPEAT_STAGE
            else -> currentDay
        }
    }


}
