package leetcode.easy.prefix_sum.range_sum_query

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NumArrayTest {

    private val sut = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))

    @Test
    fun testCorrect() {

        assertEquals(1, sut.sumRange(0, 2))
        assertEquals(-1, sut.sumRange(2,5))
        assertEquals(-3, sut.sumRange(0,5))

    }

}