package leetcode.top_interview_150.array_string.jump_game_two

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JumpGameTwoSolutionTest {

    private val sut = JumpGameTwoSolution()

    @Test
    fun testCorrect() {
        val res = sut.jump(intArrayOf(2,3,1,1,4))
        assertEquals(2, res)

        val res2 = sut.jump(intArrayOf(2,3,0,1,4))
        assertEquals(2, res2)

        val res3 = sut.jump(intArrayOf(2, 1))
        assertEquals(1, res3)
    }

}