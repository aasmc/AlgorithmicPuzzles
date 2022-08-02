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
}