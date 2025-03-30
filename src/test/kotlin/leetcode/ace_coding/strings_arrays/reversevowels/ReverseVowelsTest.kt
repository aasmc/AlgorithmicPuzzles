package leetcode.ace_coding.strings_arrays.reversevowels

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReverseVowelsTest {

    @Test
    fun reverseVowelsTest() {
        assertEquals("holle", reverseVowels("hello"))
        assertEquals("leotcede", reverseVowels("leetcode"))
        assertEquals(" ", reverseVowels(" "))
        assertEquals("Aa", reverseVowels("aA"))
    }

}