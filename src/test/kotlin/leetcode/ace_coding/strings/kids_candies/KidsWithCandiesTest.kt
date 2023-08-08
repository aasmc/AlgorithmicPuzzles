package leetcode.ace_coding.strings.kids_candies

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KidsWithCandiesTest {

    @Test
    fun test() {
        val expected = listOf(true, true, true, false, true)
        assertEquals(expected, kidsWithCandies(intArrayOf(2,3,5,1,3), 3))

        val expected2 = listOf(true,false,false,false,false)
        assertEquals(expected2, kidsWithCandies(intArrayOf(4,2,1,1,2), 1))

        val expected3 = listOf(true,false,true)
        assertEquals(expected3, kidsWithCandies(intArrayOf(12,1,12), 10))
    }

}