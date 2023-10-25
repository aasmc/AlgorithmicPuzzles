package leetcode.ace_coding.dynamic_programming.longest_common_subsequence

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LCSSolutionTest {

    private val sut = LCSSolution()

    @Test
    fun testCorrect() {

        assertEquals(3, sut.longestCommonSubsequence("abcde", "ace"))
        assertEquals(3, sut.longestCommonSubsequence("abc", "abc"))
        assertEquals(0, sut.longestCommonSubsequence("abc", "def"))

    }

}