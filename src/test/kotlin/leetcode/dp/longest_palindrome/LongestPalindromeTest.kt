package leetcode.dp.longest_palindrome

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LongestPalindromeTest {


    @Test
    fun testCorrect() {
        assertEquals("bab", longestPalindrome("babad"))
        assertEquals("bb", longestPalindrome("cbbd"))
        assertEquals("abcba", longestPalindrome("abcba"))
    }

}