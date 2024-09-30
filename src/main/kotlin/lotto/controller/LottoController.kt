package lotto.controller

import camp.nextstep.edu.missionutils.Console
import lotto.model.Lotto
import lotto.model.MatchNumber
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val resultCalculator = ResultCalculator()
    private val inputView = InputView()
    private val outputView = OutputView()

    private var winningNumbers = listOf<String>()
    private lateinit var winningAmount: String
    private lateinit var bonusNumber: String


    fun buyLotto() {
        inputView.printInputBuyAmount()
        inputWinningAmount()
        println()

        val buyLottoCount = winningAmount.toInt() / LOTTO_PRICE
        outputView.printOutputBuyAmount(buyLottoCount)
        println()

        inputView.printWinningNumbers()
        inputWinningNumbers()
        println()

        inputView.printBonusNumber()
        inputBonusNumber()
        println()

        val result = resultCalculator.calculate(lottoList, winningNumbers, bonusNumber)
        outputView.printResult(result)

        val earnMoney = getMoneySum(result)
        val purchaseMoney = winningAmount.toInt()
        val profit = earnMoney - purchaseMoney
        val ratio = (profit.toDouble() / purchaseMoney) * 100
        outputView.printProfit(ratio)
    }

    private fun inputWinningAmount() {
        var isValidInput = false

        while (!isValidInput) {
            try {
                winningAmount = Console.readLine()
                winningAmountValidationCheck(winningAmount)
                isValidInput = true
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputWinningNumbers() {
        var isValidInput = false

        while (!isValidInput) {
            try {
                winningNumbers = Console.readLine().split(",")
                winningNumberValidationCheck(winningNumbers)
                isValidInput = true
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputBonusNumber() {
        var isValidInput = false

        while (!isValidInput) {
            try {
                bonusNumber = Console.readLine()
                bonusNumberValidationCheck(bonusNumber)
                isValidInput = true
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun winningAmountValidationCheck(number: String) {
        val isNumber = number.matches(NUMBER_REGEX.toRegex())
        val isDivide = number.toInt() % 1000 == 0

        require(isNumber) {
            ("$ERROR_MESSAGE 숫자만 입력해주세요.")
        }
        require(isDivide) {
            ("$ERROR_MESSAGE 로또 구입 금액은 1000으로 나누어 떨어져야 합니다.")
        }
    }

    private fun winningNumberValidationCheck(numbers: List<String>) {
        val isValidNumber = numbers.all { it.toInt() in 1..45 }
        val isSixNumber = numbers.size == SIX_NUMBER
        val isDuplicate = numbers.distinct() == numbers

        require(isValidNumber) {
            throw IllegalArgumentException("$ERROR_MESSAGE 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
        }
        require(isSixNumber) {
            throw IllegalArgumentException("$ERROR_MESSAGE 로또 번호는 6개의 숫자여야 합니다.")
        }
        require(isDuplicate) {
            throw IllegalArgumentException("$ERROR_MESSAGE 로또 번호는 중복되지 않는 숫자여야 합니다.")
        }
    }

    private fun bonusNumberValidationCheck(number: String) {
        val isValidNumber = number.toInt() in 1..45
        val isNotDuplicate = !winningNumbers.contains(number)

        require(isValidNumber) {
            throw IllegalArgumentException("$ERROR_MESSAGE 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
        }
        require(isNotDuplicate) {
            throw IllegalArgumentException("$ERROR_MESSAGE 보너스 번호는 당첨 번호와 중복되지 않아야 합니다.")
        }
    }

    private fun getMoneySum(matchNumber: MatchNumber): Int {
        return matchNumber.fiveCount * 5000 + matchNumber.fourCount * 50000 + matchNumber.threeCount * 1000000 +
                matchNumber.twoCount * 30000000 + matchNumber.oneCount * 2000000000
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val ERROR_MESSAGE = "[ERROR]"
        const val SIX_NUMBER = 6
        const val NUMBER_REGEX = "^[0-9]+"
    }
}