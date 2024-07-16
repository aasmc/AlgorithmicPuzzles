package leetcode.medium.prefix_sum.contiguous_subarray_sum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ContiguousSubArraySumSolutionTest {

    private val sut = ContiguousSubArraySumSolution()

    @Test
    fun testCorrect() {
        assertFalse(sut.checkSubarraySum(intArrayOf(23,6,9), 6))
        assertTrue(sut.checkSubarraySum(intArrayOf(0,0), 1))
    }

}