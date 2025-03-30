package leetcode.easy.arrays.search_insert_position

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchInsertPositionSolutionTest {

    private val sut = SearchInsertPositionSolution()

    @Test
    fun testCorrect() {
        assertEquals(1, sut.searchInsert(intArrayOf(1,3,5,6), 2))
        assertEquals(2, sut.searchInsert(intArrayOf(1,3,5,6), 5))
        assertEquals(4, sut.searchInsert(intArrayOf(1,3,5,6), 7))
    }

}