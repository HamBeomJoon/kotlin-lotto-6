package lotto.view

import lotto.model.LottoGenerator

class OutputView {
    fun printOutputBuyAmount(count: Int) = println("${count}개를 구매했습니다.")
    fun printBuyNumbers(count: Int) = repeat(count) {
        val lottoGenerator = LottoGenerator()
        print("[")
        print(lottoGenerator.buyLottoNumbers.toString())
        println("]")
    }

}