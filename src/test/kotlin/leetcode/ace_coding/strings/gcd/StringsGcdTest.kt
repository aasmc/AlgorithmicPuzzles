package leetcode.ace_coding.strings.gcd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringsGcdTest {

    @Test
    fun testBruteForce() {
        val ex1 = "ABC"
        assertEquals(ex1, gcdOfStringsBruteForce("ABCABC", "ABC"))

        val ex2 = "AB"
        assertEquals(ex2, gcdOfStringsBruteForce("ABABAB", "ABAB"))

        val ex3 = ""
        assertEquals(ex3, gcdOfStringsBruteForce("LEET", "CODE"))
    }

    @Test
    fun testFastAlgo() {
        val ex1 = "ABC"
        assertEquals(ex1, gcdOfStrings("ABCABC", "ABC"))

        val ex2 = "AB"
        assertEquals(ex2, gcdOfStrings("ABABAB", "ABAB"))

        val ex3 = ""
        assertEquals(ex3, gcdOfStrings("LEET", "CODE"))
    }
}