package lotto.model

import camp.nextstep.edu.missionutils.Randoms

class LottoGenerator {
    private val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
    val buyLottoNumbers = Lotto(numbers)
}