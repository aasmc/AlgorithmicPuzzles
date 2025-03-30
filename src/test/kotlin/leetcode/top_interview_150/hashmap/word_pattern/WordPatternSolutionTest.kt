package leetcode.top_interview_150.hashmap.word_pattern

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WordPatternSolutionTest {

    private val sut = WordPatternSolution()

    @Test
    fun testCorrect() {
        assertTrue(sut.wordPattern("abba", "dog cat cat dog"))
        assertFalse(sut.wordPattern("abba", "dog cat cat fish"))
        assertFalse(sut.wordPattern("abba", "dog dog dog dog"))
    }

}