package leetcode.top_interview_150.divide_and_conquer.quad_tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuadTreeSolutionTest {

    private val sut = QuadTreeSolution()

    @Test
    fun testCorrect() {

        val grid = arrayOf(
            intArrayOf(1,1,1,1,0,0,0,0),
            intArrayOf(1,1,1,1,0,0,0,0),
            intArrayOf(1,1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,0,0,0,0),
            intArrayOf(1,1,1,1,0,0,0,0),
            intArrayOf(1,1,1,1,0,0,0,0),
            intArrayOf(1,1,1,1,0,0,0,0)
        )
        val construct = sut.construct(grid)
        println(construct)
    }

}