package leetcode.ace_coding.sliding_window.max_average_subarray

import geeks_for_geeks.algorithms.searching.equalsDelta
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MaxAverageOneTest {

    @Test
    fun maxAverageOneTestCorrect() {
        val expected = 12.75
        val res = findMaxAverage(intArrayOf(1,12,-5,-6, 50, 3), 4)
        assertTrue(expected.equalsDelta(res))
    }

}