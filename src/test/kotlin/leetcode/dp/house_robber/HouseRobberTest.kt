package leetcode.dp.house_robber

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HouseRobberTest {

    @Test
    fun testHouseRobber() {
        assertEquals(12, rob(intArrayOf(2,7,9,3,1)))
        assertEquals(4, rob(intArrayOf(1,2,3,1)))
        assertEquals(4, rob(intArrayOf(2,1,1,2)))

        assertEquals(12, rob2(intArrayOf(2,7,9,3,1)))
        assertEquals(4, rob2(intArrayOf(1,2,3,1)))
        assertEquals(4, rob2(intArrayOf(2,1,1,2)))
    }

}