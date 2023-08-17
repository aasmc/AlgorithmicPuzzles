package leetcode.dp.unique_paths2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UniquePathsWithObstaclesTest {

    @Test
    fun testCorrect() {
        assertEquals(1, uniquePathsWithObstacles(arrayOf(intArrayOf(0, 0))))
        assertEquals(0, uniquePathsWithObstacles(arrayOf(intArrayOf(1))))
        assertEquals(1, uniquePathsWithObstacles(arrayOf(intArrayOf(0))))
        assertEquals(0, uniquePathsWithObstacles(arrayOf(intArrayOf(1, 0))))
        assertEquals(2, uniquePathsWithObstacles(arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 1, 0),
            intArrayOf(0, 0, 0),
        )))
    }
}