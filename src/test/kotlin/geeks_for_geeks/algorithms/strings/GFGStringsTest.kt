package geeks_for_geeks.algorithms.strings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GFGStringsTest {
    @Test
    fun stringIsPalindrome_correct() {
        assertTrue("ABCDCBA".isPalindrome())
        assertTrue("ABBA".isPalindrome())
        assertTrue("00  00  00  aba  00  00  00".isPalindrome())
        assertFalse("GEEKS".isPalindrome())
    }

    @Test
    fun stringIsSubsequenceOfOther_Correct() {
        assertTrue("GRGES" isSubsequenceOf  "GEEKSFORGEEKS")
        assertTrue("AD" isSubsequenceOf  "ABCD")
        assertTrue("" isSubsequenceOf  "ABCD")
        assertFalse("AED" isSubsequenceOf  "ABCDEF")
        assertFalse(" " isSubsequenceOf  "ABCDEF")
    }

    @Test
    fun stringIsSubsequenceOfOtherRecursive_Correct() {
        assertTrue(isSubsequenceRecursive("GRGES", "GEEKSFORGEEKS" ) )
        assertTrue(isSubsequenceRecursive("AD",  "ABCD"))
        assertTrue(isSubsequenceRecursive("",  "ABCD"))
        assertFalse(isSubsequenceRecursive("AED",  "ABCDEF"))
        assertFalse(isSubsequenceRecursive(" ",  "ABCDEF"))
    }
}