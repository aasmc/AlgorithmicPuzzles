package leetcode.dp.delete_and_earn

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DeleteAndEarnTest {

    @Test
    fun testDeleteAndEarn() {
//        assertEquals(6, deleteAndEarn(intArrayOf(3,4,2)))
//        assertEquals(9, deleteAndEarn(intArrayOf(2,2,3,3,3,4)))
//        assertEquals(4, deleteAndEarn(intArrayOf(3,1)))
        assertEquals(18, deleteAndEarn(intArrayOf(1,1,1,2,4,5,5,5,6)))
    }

}