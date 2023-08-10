package leetcode.dp.min_cost_stairs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinCostClimbingStairsTest {

    @Test
    fun testMinCostClimbingStairs() {
        assertEquals(15, minCostClimbingStairs(intArrayOf(10, 15, 20)))
        assertEquals(6, minCostClimbingStairs(intArrayOf(1,100,1,1,1,100,1,1,100,1)))
    }

}