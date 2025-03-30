package leetcode.top_interview_150.matrix.set_zeroes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SetZeroesSolutionTest {
    private val sut = SetZeroesSolution()

    @Test
    fun testCorrect() {
        val m1 = arrayOf(
            intArrayOf(0,1,2,0),
            intArrayOf(3,4,5,2),
            intArrayOf(1,3,1,5)
        )
        sut.setZeroes(m1)
    }
}