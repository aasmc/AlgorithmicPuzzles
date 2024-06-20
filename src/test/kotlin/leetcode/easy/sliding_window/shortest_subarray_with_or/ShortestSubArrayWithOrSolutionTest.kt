package leetcode.easy.sliding_window.shortest_subarray_with_or

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShortestSubArrayWithOrSolutionTest {

    private val sut = ShortestSubArrayWithOrSolution()

    @Test
    fun testCorrect() {
        assertEquals(1, sut.minimumSubarrayLength(intArrayOf(1,2,3), 2))
        assertEquals(3, sut.minimumSubarrayLength(intArrayOf(2,1,8), 10))
        assertEquals(1, sut.minimumSubarrayLength(intArrayOf(1,2), 1))
    }

}