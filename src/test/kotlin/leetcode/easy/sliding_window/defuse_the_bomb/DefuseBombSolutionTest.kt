package leetcode.easy.sliding_window.defuse_the_bomb

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class DefuseBombSolutionTest {

    private val sut = DefuseBombSolution()

    @Test
    fun testCorrect() {
        assertContentEquals(intArrayOf(12,10,16,13), sut.decrypt(intArrayOf(5,7,1,4), 3))
        assertContentEquals(intArrayOf(0,0,0,0), sut.decrypt(intArrayOf(1,2,3,4), 0))
        assertContentEquals(intArrayOf(12,5,6,13), sut.decrypt(intArrayOf(2,4,9,3), -2))
    }

}