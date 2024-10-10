package lotto.controller

import lotto.model.Lotto
import lotto.model.MatchNumber

class ResultCalculator {
    private val matchNumber = MatchNumber()

    fun calculate(lottoList: List<Lotto>, winningNumbers: List<String>, bonusNumber: String)
            : MatchNumber {
        val winningIntNumbers = winningNumbers.map { it.toInt() }

        for (lotto in lottoList) {
            val lottoNumbers = lotto.getNumbers()
            val matchCount = lottoNumbers.count { it in winningIntNumbers }
            val bonusCount = if (bonusNumber.toInt() in lottoNumbers) 1 else 0
            val result = MatchResult.fromCounts(matchCount, bonusCount)
            result?.updateMatchCount(matchNumber)
        }

        return matchNumber
    }
}

enum class MatchResult(val matchCount: Int, val bonusCount: Int) {
    THREE(3, 0) {
        override fun updateMatchCount(matchNumber: MatchNumber) {
            matchNumber.fiveCount++
        }
    },
    FOUR(4, 0) {
        override fun updateMatchCount(matchNumber: MatchNumber) {
            matchNumber.fourCount++
        }
    },
    FIVE(5, 0) {
        override fun updateMatchCount(matchNumber: MatchNumber) {
            matchNumber.threeCount++
        }
    },
    SIX_WITH_BONUS(6, 1) {
        override fun updateMatchCount(matchNumber: MatchNumber) {
            matchNumber.twoCount++
        }
    },
    SIX_WITHOUT_BONUS(6, 0) {
        override fun updateMatchCount(matchNumber: MatchNumber) {
            matchNumber.oneCount++
        }
    };

    abstract fun updateMatchCount(matchNumber: MatchNumber)

    companion object {
        fun fromCounts(matchCount: Int, bonusCount: Int): MatchResult? {
            return when {
                matchCount == 3 -> THREE
                matchCount == 4 -> FOUR
                matchCount == 5 -> FIVE
                matchCount == 6 && bonusCount == 1 -> SIX_WITH_BONUS
                matchCount == 6 && bonusCount == 0 -> SIX_WITHOUT_BONUS
                else -> null
            }
        }
    }
}