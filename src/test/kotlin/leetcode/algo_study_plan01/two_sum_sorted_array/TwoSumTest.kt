package leetcode.algo_study_plan01.two_sum_sorted_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TwoSumTest {

    @Test
    fun twoSum_correctPairAtStart() {
        val input = intArrayOf(2, 7, 11, 15)
        val target = 9
        val expected = intArrayOf(1, 2)
        val result = twoSum(input, target)
        testAssertion(result, expected)
    }

    @Test
    fun twoSum_correctPairMixed() {
        val input = intArrayOf(2,3,4)
        val target = 6
        val expected = intArrayOf(1, 3)
        val result = twoSum(input, target)
        testAssertion(result, expected)
    }

    @Test
    fun twoSum_targetNegative() {
        val input = intArrayOf(-1, 0)
        val target = -1
        val expected = intArrayOf(1, 2)
        val result = twoSum(input, target)
        testAssertion(result, expected)
    }

    private fun testAssertion(result: IntArray, expected: IntArray) {
        for (i in result.indices) {
            assertEquals(expected[i], result[i])
        }
    }
}