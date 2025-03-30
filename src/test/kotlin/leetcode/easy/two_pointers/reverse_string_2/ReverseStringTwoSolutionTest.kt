package leetcode.easy.two_pointers.reverse_string_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReverseStringTwoSolutionTest {

    private val sut = ReverseStringTwoSolution()

    @Test
    fun testCorrect() {

        assertEquals("bacdfeg", sut.reverseStr("abcdefg", 2))
        assertEquals("bacd", sut.reverseStr("abcd", 2))
        assertEquals("gfedcba", sut.reverseStr("abcdefg", 8))

    }

}