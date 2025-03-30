package leetcode.dp.tribonacci

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TribonacciTest {
    @Test
    fun testTribonacci() {
        assertEquals(1389537, tribonacci(25))
        assertEquals(1389537, tribonacci2(25))
    }
}