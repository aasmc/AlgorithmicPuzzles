package leetcode.dp.climbing_stairs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

class ClimbStairsTest {

    @Test
    fun climbStairsTest() {
        assertEquals(2, climbStairs(2))
        assertEquals(1, climbStairs(1))
        assertEquals(3, climbStairs(3))
    }

}