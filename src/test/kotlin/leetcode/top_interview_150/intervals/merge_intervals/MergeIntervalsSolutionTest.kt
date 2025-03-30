package leetcode.top_interview_150.intervals.merge_intervals

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MergeIntervalsSolutionTest {

    private val sut = MergeIntervalsSolution()

    @Test
    fun testCorrect() {
        val in1 = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18),
        )
        val ex1 = arrayOf(
            intArrayOf(1, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18)
        )
        val res1 = sut.merge(in1)
        for (i in res1.indices) {
            for (j in res1[i].indices) {
                assertEquals(ex1[i][j], res1[i][j])
            }
        }
    }

}