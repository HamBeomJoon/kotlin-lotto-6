package lotto.view

class OutputView {
    fun printOutputBuyAmount(amount: String) = println("${amount.toInt() / 1000} 개를 구매했습니다.")
    fun printBuyNumbers() = println()
    fun printOutputBuyAmount(count: Int) = println("${count}개를 구매했습니다.")
}