package lotto.view

import lotto.model.Lotto
import lotto.model.MatchNumber

class OutputView {
    fun printOutputBuyAmount(count: Int) {
        println("${count}개를 구매했습니다.")
    }


    fun printResult(matchNumber: MatchNumber) {
        println("당첨 통계")
        println("---")
        println("3개 일치 (5,000원) - ${matchNumber.fiveCount}개")
        println("4개 일치 (50,000원) - ${matchNumber.fourCount}개")
        println("5개 일치 (1,000,000원) - ${matchNumber.threeCount}개")
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - ${matchNumber.twoCount}개")
        println("6개 일치 (2,000,000,000원) - ${matchNumber.oneCount}개")
    }

    fun printProfit(ratio: Double) = println("총 수익률은 ${"%.1f".format(ratio)}%입니다.")
}