package leetcode.dp.maximal_square

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaximalSquareTest {

    @Test
    fun testCorrect() {
        assertEquals(4, maximalSquare(arrayOf(
            charArrayOf('1','0','1','0','0'),
            charArrayOf('1','0','1','1','1'),
            charArrayOf('1','1','1','1','1'),
            charArrayOf('1','0','0','1','0'),
        )))

        assertEquals(1, maximalSquare(arrayOf(
            charArrayOf('0','1'),
            charArrayOf('1','0'),
        )))
    }

}