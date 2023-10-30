package leetcode.top_interview_150.array_string.merge_sorted_arrays

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MergeSortedSolutionTest {

    private val sut = MergeSortedSolution()

    @Test
    fun testCorrect() {
        test(intArrayOf(1,2,3,0,0,0), 3, intArrayOf(2,5,6), 3, intArrayOf(1,2,2,3,5,6))
        test(intArrayOf(1), 1, intArrayOf(), 0, intArrayOf(1))
        test(intArrayOf(0), 0, intArrayOf(1), 1, intArrayOf(1))
        test(intArrayOf(1,2,4,8,9,0,0,0), 5, intArrayOf(2,5,10), 3, intArrayOf(1,2,2,4,5,8,9,10))
    }

    private fun test(left: IntArray, m: Int, right: IntArray, n: Int, expected: IntArray) {
        sut.merge(left, m, right, n)
        for (i in left.indices) {
            assertEquals(left[i], expected[i])
        }
    }

}