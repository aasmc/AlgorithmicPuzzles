package leetcode.ace_coding.sliding_window.max_vowels_in_substr

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxVowelsTest {

    @Test
    fun maxVowelsTestCorrect() {

        assertEquals(3, maxVowels("abciiidef", 3))
        assertEquals(2, maxVowels("aeiou", 2))
        assertEquals(2, maxVowels("leetcode", 3))

    }

}