package leetcode.top_interview_150.array_string.integer_to_roman

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IntegerToRomanSolutionTest {

    private val sut = IntegerToRomanSolution()

    @Test
    fun testCorrect() {
        assertEquals("III", sut.intToRoman(3))
        assertEquals("LVIII", sut.intToRoman(58))
        assertEquals("MCMXCIV", sut.intToRoman(1994))
        assertEquals("XX", sut.intToRoman(20))
    }
}