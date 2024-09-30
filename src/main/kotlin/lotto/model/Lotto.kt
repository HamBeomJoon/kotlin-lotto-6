package lotto.model

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) {}
        require(numbers.all { it in 1..45 })
        require(numbers.distinct().count() == numbers.size)
    }

    fun getNumbers(): List<Int> {
        return numbers
    }
}
