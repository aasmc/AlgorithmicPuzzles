package leetcode.ace_coding.heap_priority.max_subsequence_score

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxSubsequenceScoreSolutionTest {

    val sut = MaxSubsequenceScoreSolution()

    @Test
    fun testCorrect() {
        val score = sut.maxScore(intArrayOf(1,3,3,2), intArrayOf(2,1,3,4), 3)
        assertEquals(12L, score)
    }

}