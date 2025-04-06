package leetcode.ace_coding.strings_arrays.reverse_words

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReverseWordsTest {

    @Test
    fun testCorrect() {
        val result = reverseWords("the sky is blue")
        assertEquals("blue is sky the", result)
    }

}