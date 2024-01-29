package leetcode.top_interview_150.matrix.spiral

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.asserter

class SpiralMatrixSolutionTest {

    private val sut = SpiralMatrixSolution()

    @Test
    fun testCorrect() {

        val in1 = arrayOf<IntArray>(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9),
        )
        val ex1 = listOf(1,2,3,6,9,8,7,4,5)
        assertEquals(ex1, sut.spiralOrder(in1))

        val in2 = arrayOf(
            intArrayOf(1,2,3,4),
            intArrayOf(5,6,7,8),
            intArrayOf(9,10,11,12)
        )
        val ex2 = listOf(1,2,3,4,8,12,11,10,9,5,6,7)
        assertEquals(ex2, sut.spiralOrder(in2))
    }

}