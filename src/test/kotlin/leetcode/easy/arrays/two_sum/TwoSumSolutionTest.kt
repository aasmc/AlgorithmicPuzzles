package leetcode.easy.arrays.two_sum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TwoSumSolutionTest {

    val sut = TwoSumSolution()

    @Test
    fun testCorrect() {
        val input = intArrayOf(2,7,11,15)
        val target = 9
        val res = sut.twoSum(input, target)
        assertEquals(0, res[0])
        assertEquals(1, res[1])
    }

}