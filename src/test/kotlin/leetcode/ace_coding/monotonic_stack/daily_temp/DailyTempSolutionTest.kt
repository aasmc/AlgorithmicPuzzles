package leetcode.ace_coding.monotonic_stack.daily_temp

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DailyTempSolutionTest {

    private val sut = DailyTempSolution()

    @Test
    fun testCorrect() {
        val input = intArrayOf(73,74,75,71,69,72,76,73)
        val expected = intArrayOf(1,1,4,2,1,1,0,0)
        assertTrue(sut.dailyTemperatures(input).contentEquals(expected))
    }

}