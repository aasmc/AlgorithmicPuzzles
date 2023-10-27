package leetcode.ace_coding.monotonic_stack.online_stock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StockSpannerTest {

    private val spanner = StockSpanner()

    @Test
    fun testCorrect() {
        assertEquals(1, spanner.next(100))
        assertEquals(1, spanner.next(80))
        assertEquals(1, spanner.next(60))
        assertEquals(2, spanner.next(70))
        assertEquals(1, spanner.next(60))
        assertEquals(4, spanner.next(75))
        assertEquals(6, spanner.next(85))

    }

}