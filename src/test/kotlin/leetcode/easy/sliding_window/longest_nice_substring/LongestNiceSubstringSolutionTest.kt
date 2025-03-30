package leetcode.easy.sliding_window.longest_nice_substring

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestNiceSubstringSolutionTest {

    private val sut = LongestNiceSubstringSolution()

    @Test
    fun testCorrect() {
        assertEquals("aAa", sut.longestNiceSubstring("YazaAay"))
    }

}