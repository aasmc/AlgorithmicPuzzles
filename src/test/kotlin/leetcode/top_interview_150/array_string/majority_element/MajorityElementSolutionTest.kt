package leetcode.top_interview_150.array_string.majority_element

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MajorityElementSolutionTest {

    private val sut = MajorityElementSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.majorityElement(intArrayOf(1,3,7,3,7,3)))
    }

}