package leetcode.dp.triangle

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinimumTotalTriangleTest {

    @Test
    fun testCorrect() {
        assertEquals(11, minimumTotal(listOf(
            listOf(2),
            listOf(3,4),
            listOf(6,5,7),
            listOf(4,1,8,3),
        )))

        assertEquals(-1, minimumTotal(listOf(
            listOf(-1),
            listOf(2,3),
            listOf(1, -1, -3),
        )))

        assertEquals(-10, minimumTotal(listOf(
            listOf(-10)
        )))
    }

}