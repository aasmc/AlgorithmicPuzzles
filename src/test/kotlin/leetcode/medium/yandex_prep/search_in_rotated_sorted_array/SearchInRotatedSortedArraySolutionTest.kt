package leetcode.medium.yandex_prep.search_in_rotated_sorted_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchInRotatedSortedArraySolutionTest {

    private val sut = SearchInRotatedSortedArraySolution()

    @Test
    fun testCorrect() {

//        assertEquals(4, sut.search(intArrayOf(4,5,6,7,0,1,2), 0))
//        assertEquals(1, sut.search(intArrayOf(1, 3), 3))
//        assertEquals(1, sut.search(intArrayOf(3, 1), 1))
        assertEquals(6, sut.search(intArrayOf(3,4,5,6,7,1,2), 2))

    }

}