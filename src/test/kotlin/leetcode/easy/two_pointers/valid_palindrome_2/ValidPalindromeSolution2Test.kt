package leetcode.easy.two_pointers.valid_palindrome_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidPalindromeSolution2Test {

    private val sut = ValidPalindromeSolution2()

    @Test
    fun testCorrect() {

        assertTrue(sut.validPalindrome("aba"))
        assertTrue(sut.validPalindrome("abca"))
        assertFalse(sut.validPalindrome("abc"))

    }

}