package leetcode.dp.minimum_failing_path

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinimumFailingPathTest {

    @Test
    fun testCorrect() {
        assertEquals(-59, minFallingPathSum(arrayOf(
            intArrayOf(-19, 57),
            intArrayOf(-40, -5),
        )))

        assertEquals(13, minFallingPathSum(arrayOf(
            intArrayOf(2,1,3),
            intArrayOf(6,5,4),
            intArrayOf(7,8,9),
        )))
    }

}