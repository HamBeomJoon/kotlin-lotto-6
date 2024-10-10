package lotto

import lotto.controller.LottoController
import lotto.model.Lotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class InputTest {
    private val controller =  LottoController()

    @Test
    fun `로또 구입 금액에 문자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = "aa"
            controller.winningAmountValidationCheck(invalidInput)
        }
    }

    @Test
    fun `로또 구입 금액이 1000원 미만이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = "100"
            controller.winningAmountValidationCheck(invalidInput)
        }
    }

    @Test
    fun `로또 구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = "1120"
            controller.winningAmountValidationCheck(invalidInput)
        }
    }

    @Test
    fun `당첨 번호에 문자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = listOf("1","a")
            controller.winningNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `당첨 번호가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = listOf("1","2","3","4","5","6","7")
            controller.winningNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `당첨 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = listOf("1","2","3","4","5","5")
            controller.winningNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `당첨 번호에 1 ~ 45 범위 밖의 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = listOf("1","2","3","4","5","60")
            controller.winningNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `보너스 번호에 문자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = "aa"
            controller.bonusNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `보너스 번호에 1 ~ 45 범위 밖의 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val invalidInput = "47"
            controller.bonusNumberValidationCheck(invalidInput)
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호 숫자와 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            controller.winningNumbers = listOf("12","1","2","3","4","5")
            val invalidInput = "12"
            controller.bonusNumberValidationCheck(invalidInput)
        }
    }
}
