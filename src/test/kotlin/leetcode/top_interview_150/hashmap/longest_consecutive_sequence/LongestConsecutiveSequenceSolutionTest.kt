package leetcode.top_interview_150.hashmap.longest_consecutive_sequence

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestConsecutiveSequenceSolutionTest {

    private val sut = LongestConsecutiveSequenceSolution()

    @Test
    fun testCorrect() {

        assertEquals(4, sut.longestConsecutive(intArrayOf(100,4,200,1,3,2)))
        assertEquals(9, sut.longestConsecutive(intArrayOf(0,3,7,2,5,8,4,6,0,1)))

    }

}