package leetcode.top_interview_150.sliding_window.minimum_size_subarray_sum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinSizeBSubarraySumSolutionTest {
    private val sut = MinSizeBSubarraySumSolution()

    @Test
    fun testCorrect() {
        assertEquals(2, sut.minSubArrayLen(7, intArrayOf(2,3,1,2,4,3)))
        assertEquals(1, sut.minSubArrayLen(4, intArrayOf(1, 4, 4)))
        assertEquals(0, sut.minSubArrayLen(11, intArrayOf(1,1,1,1,1,1,1,1)))
    }
}