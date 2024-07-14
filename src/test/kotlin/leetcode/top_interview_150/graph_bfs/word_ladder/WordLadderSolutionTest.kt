package leetcode.top_interview_150.graph_bfs.word_ladder

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WordLadderSolutionTest {

    private val sut = WordLadderSolution()

    @Test
    fun testCorrect() {

        assertEquals(0, sut.ladderLength("hot", "dog", listOf("hot", "dog")))

    }

}