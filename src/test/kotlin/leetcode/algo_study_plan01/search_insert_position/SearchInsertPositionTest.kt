package leetcode.algo_study_plan01.search_insert_position

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SearchInsertPositionTest {

    @Test
    fun targetInArray_returnsCorrectIndex() {
        val input = intArrayOf(1, 3, 5, 6)
        val target = 5
        val expected = 2

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun targetNotInArray_mustBeInTheMiddle_returnsCorrectIndex() {
        val input = intArrayOf(1, 3, 5, 6)
        val target = 2
        val expected = 1

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun targetNotInArray_mustBeInAtTheEnd_returnsCorrectIndex() {
        val input = intArrayOf(1, 3, 5, 6)
        val target = 7
        val expected = 4

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun targetNotInArray_mustBeInAtTheStart_returnsCorrectIndex() {
        val input = intArrayOf(1, 3, 5, 6)
        val target = 0
        val expected = 0

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun targetNotInArray_arrayConsistsOfSingleValue_mustBeInAtTheStart_returnsCorrectIndex() {
        val input = intArrayOf(1)
        val target = 0
        val expected = 0

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun testIdentity() {
        val input = intArrayOf(1)
        val target = 1
        val expected = 0

        assertEquals(expected, searchInsert(input, target))
    }

    @Test
    fun testTwo_three() {
        val input = intArrayOf(1, 3)
        val target = 2
        val expected = 1

        assertEquals(expected, searchInsert(input, target))
    }

}
















