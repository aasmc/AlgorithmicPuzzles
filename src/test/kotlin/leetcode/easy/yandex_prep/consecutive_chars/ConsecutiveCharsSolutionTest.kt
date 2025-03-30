package leetcode.easy.yandex_prep.consecutive_chars

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConsecutiveCharsSolutionTest {

    @Test
    fun testCorrect() {

        val sut = ConsecutiveCharsSolution()

        assertEquals(5, sut.maxPower("abbcccddddeeeeedcba"))

    }

}