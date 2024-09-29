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
    }
