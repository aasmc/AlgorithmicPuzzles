package leetcode.top_interview_150.array_string.h_index

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HIndexSolutionTest {

    private val sut = HIndexSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.hIndex(intArrayOf(3,0,6,1,5)))
        assertEquals(1, sut.hIndex(intArrayOf(1, 3, 1)))
    }

}

