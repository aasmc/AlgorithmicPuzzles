package leetcode.top_interview_150.sliding_window.minimum_window_substring

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinimumWindowSubstringSolutionTest {

    private val sut = MinimumWindowSubstringSolution()

    @Test
    fun testCorrect() {
//        assertEquals("BANC", sut.minWindow("ADOBECODEBANC", "ABC"))
//        assertEquals("a", sut.minWindow("a", "a"))
//        assertEquals("", sut.minWindow("a", "aa"))
//        assertEquals("aa", sut.minWindow("aa", "aa"))
//        assertEquals("ba", sut.minWindow("bba", "ab"))
        assertEquals("baca", sut.minWindow("acbbaca", "aba"))
    }

}