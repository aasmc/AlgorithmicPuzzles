package leetcode.medium.intervals.insert_interval

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class InsertIntervalSolutionTest {

    private val sut = InsertIntervalSolution()

    @Test
    fun testCorrect() {

        val expected = arrayOf(intArrayOf(1,8))
        val newInterval = intArrayOf(5,6)
        val input = arrayOf(intArrayOf(1,5), intArrayOf(6,8))

        val result = sut.insert(input,newInterval)
        for (i in result.indices) {
            assertContentEquals(expected[i], result[i])
        }

    }

}