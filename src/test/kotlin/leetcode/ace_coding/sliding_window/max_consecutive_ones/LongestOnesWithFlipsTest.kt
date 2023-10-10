package leetcode.ace_coding.sliding_window.max_consecutive_ones

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestOnesWithFlipsTest {

    @Test
    fun longestOneTestCorrect() {

        assertEquals(6, longestOnes(intArrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))
        assertEquals(10, longestOnes(intArrayOf(0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1), 3))


    }

}