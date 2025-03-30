package leetcode.algo_study_plan01.binary_search

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BinarySearchTest {

    @Test
    fun binarySearchTargetInArray_correctOutput() {
        val input = intArrayOf(-1,0,3,5,9,12)
        val target = 9
        val expected = 4
        assertEquals(expected, binarySearch(input, target))
    }

    @Test
    fun binarySearchTargetNotInArray_correctOutput() {
        val input = intArrayOf(-1,0,3,5,9,12)
        val target = 2
        val expected = -1
        assertEquals(expected, binarySearch(input, target))
    }
}