package leetcode.algo_study_plan01.squares_of_a_sorted_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SquaresOfSortedArrayTest {

    @Test
    fun sortArrayWithZero_correctOutput() {
        val input = intArrayOf(-4,-1,0,3,10)
        val expected = intArrayOf(0,1,9,16,100)
        val result = sortedSquares(input)
        for (i in expected.indices) {
            assertEquals(expected[i],result[i])
        }
    }

    @Test
    fun sortArrayWithoutZero_correctOutput() {
        val input = intArrayOf(-7,-3,2,3,11)
        val expected = intArrayOf(4,9,9,49,121)
        val result = sortedSquares(input)
        for (i in expected.indices) {
            assertEquals(expected[i],result[i])
        }
    }
}