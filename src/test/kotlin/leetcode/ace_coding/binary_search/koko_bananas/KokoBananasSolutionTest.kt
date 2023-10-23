package leetcode.ace_coding.binary_search.koko_bananas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KokoBananasSolutionTest {

    private val sut = KokoBananasSolution()

    @Test
    fun testCorrect() {
        val piles = intArrayOf(3,6,7,11)
        assertEquals(4, sut.minEatingSpeed(piles, 8))
    }

}