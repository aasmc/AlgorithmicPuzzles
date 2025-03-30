package leetcode.ace_coding.graph_bfs.nearest_exit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NearestExitSolutionTest {
    private val sut = NearestExitSolution()
    @Test
    fun testCorrect() {
        val maze = arrayOf(
            charArrayOf('+', '+', '+'),
            charArrayOf('.', '.', '.'),
            charArrayOf('+', '+', '+'),
        )
        val entrance = intArrayOf(1, 0)
        val res = sut.nearestExit(maze, entrance)
        assertEquals(2, res)
    }

}