package lotto.controller

import camp.nextstep.edu.missionutils.Console
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    private lateinit var winningAmount: String
    fun buyLotto() {
        inputView.printInputBuyAmount()
        inputWinningAmount()

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
    companion object {
        const val ERROR_MESSAGE = "[ERROR]"
        const val SIX_NUMBER = 6
        const val NUMBER_REGEX = "^[0-9]+"
    }
}