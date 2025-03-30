package leetcode.medium.yandex_prep

import leetcode.medium.yandex_prep.longest_subarray_of_ones.LongestSubarrayOfOnes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestSubarrayOfOnesTest {

    private val sut = LongestSubarrayOfOnes()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.longestSubarray(intArrayOf(1,1,0,1)))
        assertEquals(5, sut.longestSubarray(intArrayOf(0,1,1,1,0,1,1,0,1)))
    }

}