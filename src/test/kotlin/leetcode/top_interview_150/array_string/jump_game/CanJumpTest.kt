package leetcode.top_interview_150.array_string.jump_game

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CanJumpTest {

    @Test
    fun canJumpCorrect() {
        assertTrue(canJump(intArrayOf(2,3,1,1,4)))
        assertFalse(canJump(intArrayOf(3,2,1,0,4)))
        assertTrue(canJump(intArrayOf(1)))
        assertTrue(canJump(intArrayOf(1,2,3)))
    }

}