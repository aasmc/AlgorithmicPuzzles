package leetcode.easy.graph.town_judge

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TownJudgeSolutionTest {

    private val sut = TownJudgeSolution()

    @Test
    fun testCorrect() {

        assertEquals(2, sut.findJudge(2, arrayOf(intArrayOf(1,2))))
        assertEquals(3, sut.findJudge(3, arrayOf(intArrayOf(1,3), intArrayOf(2,3))))
        assertEquals(-1, sut.findJudge(3, arrayOf(intArrayOf(1,3), intArrayOf(2,3), intArrayOf(3,1))))

    }

}