package leetcode.ace_coding.stack.decode_string

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DecodeStringTest {

    @Test
    fun decodeStringTest() {
        val ex1 = "aaabcbc"
        assertEquals(ex1, decodeString("3[a]2[bc]"))
        val ex2 = "accaccacc"
        assertEquals(ex2, decodeString("3[a2[c]]"))
        val ex3 = "abcabccdcdcdef"
        assertEquals(ex3, decodeString("2[abc]3[cd]ef"))
        val ex4 = "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
        assertEquals(ex4, decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"))
    }

}