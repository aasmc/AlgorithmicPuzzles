package leetcode.dp.minimum_path_sum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinPathSumTest {

    @Test
    fun testMinPathSum() {
//        assertEquals(7, minPathSum(arrayOf(
//            intArrayOf(1,3,1),
//            intArrayOf(1,5,1),
//            intArrayOf(4,2,1)
//        )))

        assertEquals(12, minPathSum(arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
        )))
    }

}