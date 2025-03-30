package leetcode.top_interview_150.hashmap.ransom_note

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RansomNoteSolutionTest {

    private val sut = RansomNoteSolution()

    @Test
    fun testCorrect() {

        assertFalse(sut.canConstruct("a", "b"))
        assertFalse(sut.canConstruct("aa", "ab"))
        assertTrue(sut.canConstruct("aa", "aab"))

    }

}