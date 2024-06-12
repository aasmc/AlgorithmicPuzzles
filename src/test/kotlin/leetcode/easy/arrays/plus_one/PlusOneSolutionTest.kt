package leetcode.easy.arrays.plus_one

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class PlusOneSolutionTest {

    private val sut = PlusOneSolution()

    @Test
    fun testCorrect() {
        assertContentEquals(intArrayOf(1,2,4), sut.plusOne(intArrayOf(1,2,3)))
        assertContentEquals(intArrayOf(4,3,2,2), sut.plusOne(intArrayOf(4,3,2,1)))
        assertContentEquals(intArrayOf(1,0,0,0), sut.plusOne(intArrayOf(9,9,9)))
    }

}