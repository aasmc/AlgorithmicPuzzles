package leetcode.top_interview_150.array_string.roman_to_integer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RomanToIntegerSolutionTest {
    private val sut = RomanToIntegerSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.romanToInt("III"))
        assertEquals(58, sut.romanToInt("LVIII"))
        assertEquals(1994, sut.romanToInt("MCMXCIV"))
    }
}