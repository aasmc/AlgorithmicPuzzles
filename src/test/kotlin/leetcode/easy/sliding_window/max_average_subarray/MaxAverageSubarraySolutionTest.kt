package leetcode.easy.sliding_window.max_average_subarray

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxAverageSubarraySolutionTest {

    private val sut = MaxAverageSubarraySolution()

    @Test
    fun testCorrect() {

        assertEquals(12.75, sut.findMaxAverage(intArrayOf(1,12,-5,-6,50,3), 4))
        assertEquals(5.0, sut.findMaxAverage(intArrayOf(5), 1))

    }

}