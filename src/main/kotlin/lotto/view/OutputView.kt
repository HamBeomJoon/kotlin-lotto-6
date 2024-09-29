package lotto.view

class OutputView {
    fun printOutputBuyAmount(amount: String) = println("${amount.toInt() / 1000} 개를 구매했습니다.")
}