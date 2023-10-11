package leetcode.prefix_sum.highest_altitude

import leetcode.ace_coding.prefix_sum.highest_altitude.largestAltitude
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LargestAltitudeTest {


    @Test
    fun largestAltitudeTestCorrect() {
        assertEquals(1, largestAltitude(intArrayOf(-5,1,5,0,-7)))
        assertEquals(0, largestAltitude(intArrayOf(-4,-3,-2,-1,4,3,2)))
    }

}