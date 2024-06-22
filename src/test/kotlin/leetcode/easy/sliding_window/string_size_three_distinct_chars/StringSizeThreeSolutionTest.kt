package leetcode.easy.sliding_window.string_size_three_distinct_chars

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringSizeThreeSolutionTest {

    private val sut = StringSizeThreeSolution()

    @Test
    fun testCorrect() {
//        assertEquals(1, sut.countGoodSubstrings("xyzzaz"))
        assertEquals(4, sut.countGoodSubstrings("aababcabc"))
    }

}