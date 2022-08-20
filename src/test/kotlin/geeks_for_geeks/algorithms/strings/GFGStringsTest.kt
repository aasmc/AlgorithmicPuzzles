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

    @Test
    fun stringIsAnagramWithOtherString_correct() {
        assertTrue("listen".isAnagramWith("silent"))
        assertTrue("aaacb".isAnagramWith("acbaa"))
        assertTrue("they see".isAnagramWith("the eyes"))
        assertTrue("i learned words".isAnagramWith("older and wiser"))
        assertFalse("aab".isAnagramWith("bab"))
    }

    @Test
    fun leftMostRepeatingCharacter_correct() {
        assertEquals(0, "geeksforgeeks".leftMostRepeatingCharacterIndex())
        assertEquals(1, "abbcc".leftMostRepeatingCharacterIndex())
        assertEquals(1, "abccb".leftMostRepeatingCharacterIndex())
        assertEquals(-1, "abcd".leftMostRepeatingCharacterIndex())
    }

    @Test
    fun leftMostRepeatingCharacterIndexImproved_correct() {
        assertEquals(0, "geeksforgeeks".leftMostRepeatingCharacterIndexImproved())
        assertEquals(1, "abbcc".leftMostRepeatingCharacterIndexImproved())
        assertEquals(1, "abccb".leftMostRepeatingCharacterIndexImproved())
        assertEquals(-1, "abcd".leftMostRepeatingCharacterIndexImproved())
    }

    @Test
    fun leftMostRepeatingCharacterIndexImprovedVersionTwo_correct() {
        assertEquals(0, "geeksforgeeks".leftMostCharacterRepeatingImprovedVersionTwo())
        assertEquals(1, "abbcc".leftMostCharacterRepeatingImprovedVersionTwo())
        assertEquals(1, "abccb".leftMostCharacterRepeatingImprovedVersionTwo())
        assertEquals(-1, "abcd".leftMostCharacterRepeatingImprovedVersionTwo())
    }

    @Test
    fun leftMostNonRepeatingCharacterIndex_correct() {
        assertEquals(5, "geeksforgeeks".indexOfLeftmostNonRepeatingChar())
        assertEquals(0, "abbcc".indexOfLeftmostNonRepeatingChar())
        assertEquals(-1, "abcabc".indexOfLeftmostNonRepeatingChar())
        assertEquals(0, "apple".indexOfLeftmostNonRepeatingChar())
    }

    @Test
    fun leftMostNonRepeatingCharacterIndexImproved_correct() {
        assertEquals(5, "geeksforgeeks".indexOfLeftmostNonRepeatingCharImproved())
        assertEquals(0, "abbcc".indexOfLeftmostNonRepeatingCharImproved())
        assertEquals(-1, "abcabc".indexOfLeftmostNonRepeatingCharImproved())
        assertEquals(0, "apple".indexOfLeftmostNonRepeatingCharImproved())
    }

    @Test
    fun reverseWordsString_correct() {
        assertEquals("gfg to welcome", "welcome to gfg".reverseWords())
        assertEquals("coding love i", "i love coding".reverseWords())
        assertEquals("abc", "abc".reverseWords())
    }



    @Test
    fun reverseWordsCharArray_correct() {
        assertEquals("gfg to welcome", reverseWords("welcome to gfg".toCharArray()))
        assertEquals("coding love i", reverseWords("i love coding".toCharArray()))
        assertEquals("abc", reverseWords("abc".toCharArray()))
    }













}