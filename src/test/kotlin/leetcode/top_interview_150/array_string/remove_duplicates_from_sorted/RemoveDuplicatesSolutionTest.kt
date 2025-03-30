package leetcode.top_interview_150.array_string.remove_duplicates_from_sorted

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RemoveDuplicatesSolutionTest {

    private val sut = RemoveDuplicatesSolution()

    @Test
    fun testCorrect() {
        test(intArrayOf(1,1,1,2,2,3), intArrayOf(1,1,2,2,3))
        test(intArrayOf(0,0,1,1,1,1,2,3,3), intArrayOf(0,0,1,1,2,3,3))
    }

    private fun test(input: IntArray, expected: IntArray) {
        val r = sut.removeDuplicates(input)
        for (i in 0 until r) {
            assertEquals(input[i], expected[i])
        }
    }
}