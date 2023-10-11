package leetcode.ace_coding.prefix_sum.pivot_index

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PivotIndexTest {

    @Test
    fun pivotIndexTestCorrect() {
        assertEquals(3, pivotIndex(intArrayOf(1,7,3,6,5,6)))
        assertEquals(-1, pivotIndex(intArrayOf(1,2,3)))
        assertEquals(0, pivotIndex(intArrayOf(2,1,-1)))
    }

}