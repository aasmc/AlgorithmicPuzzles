package leetcode.medium.yandex_prep.find_min_in_rotated_sorted_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FindMinInRotatedSortedArraySolutionTest {

    private val sut = FindMinInRotatedSortedArraySolution()

    @Test
    fun testCorrect() {

        assertEquals(1, sut.findMin(intArrayOf(3,1,2)))

    }

}