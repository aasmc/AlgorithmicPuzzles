package leetcode.medium.yandex_prep.subarray_sum_equals_k

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SubarraySumSolutionTest {

    private val sut = SubarraySumSolution()

    @Test
    fun testCorrect() {
        assertEquals(1, sut.subarraySum(intArrayOf(-1,-1, 1), 0))
        assertEquals(2, sut.subarraySum(intArrayOf(1,1,1), 2))
        assertEquals(2, sut.subarraySum(intArrayOf(1,2,3), 3))
        assertEquals(0, sut.subarraySum(intArrayOf(1), 0))
    }

}