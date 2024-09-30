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
            var bonusCount = 0
            val matchCount = lottoNumbers.count { it in winningIntNumbers }
            if (bonusNumber.toInt() in lottoNumbers) {
                bonusCount++
            }
            resultCalculate(matchCount, bonusCount)
        }

        return matchNumber
    }

    private fun resultCalculate(matchCount: Int, bonusCount: Int) {
        when (matchCount + bonusCount) {
            3 -> matchNumber.fiveCount++
            4 -> matchNumber.fourCount++
            5 -> matchNumber.threeCount++
            6 -> {
                if (matchCount == 6) {
                    matchNumber.oneCount++
                } else {
                    matchNumber.twoCount++
                }
            }
        }
    }
}