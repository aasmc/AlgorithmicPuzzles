package leetcode.ace_coding.two_pointers.water_container

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxAreaTest {


    @Test
    fun maxAreaTestCorrect() {
        assertEquals(49, maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
        assertEquals(1, maxArea(intArrayOf(1, 1)))
    }

}