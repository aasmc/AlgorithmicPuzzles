package leetcode.ace_coding.intervals.arrows_balloons

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArrowsBalloonsSolutionTest {

    private val sut = ArrowsBalloonsSolution()

    @Test
    fun testCorrect() {
        val input = arrayOf(
            intArrayOf(10, 16),
            intArrayOf(2, 8),
            intArrayOf(1, 6),
            intArrayOf(7, 12)
        )
        val res = sut.findMinArrowShots(input)
        assertEquals(2, res)
    }

}