package leetcode.easy.sliding_window.longest_even_odd_subarray

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestEvenOddSubarraySolutionTest {

    private val sut = LongestEvenOddSubarraySolution()

    @Test
    fun testCorrect() {

        assertEquals(3, sut.longestAlternatingSubarray(intArrayOf(3,2,5,4), 5))
        assertEquals(1, sut.longestAlternatingSubarray(intArrayOf(1,2), 2))
        assertEquals(1, sut.longestAlternatingSubarray(intArrayOf(2,10,5), 7))
        assertEquals(3, sut.longestAlternatingSubarray(intArrayOf(2,2,5,1,6,7,8), 17))

    }

}