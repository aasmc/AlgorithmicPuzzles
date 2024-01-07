package leetcode.top_interview_150.two_pointers.valid_palindrome

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidPalindromeSolutionTest {

    private val sut = ValidPalindromeSolution()

    @Test
    fun testCorrect() {
        assertTrue(sut.isPalindrome("A man, a plan, a canal: Panama"))
        assertFalse(sut.isPalindrome("race a car"))
        assertTrue(sut.isPalindrome(" "))
    }

}