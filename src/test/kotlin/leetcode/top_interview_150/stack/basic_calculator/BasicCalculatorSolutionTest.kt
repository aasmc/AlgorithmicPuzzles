package leetcode.top_interview_150.stack.basic_calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BasicCalculatorSolutionTest {

    private val sut = BasicCalculatorSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.calculate("2-1 + 2"))

        assertEquals(2, sut.calculate("1 + 1"))
        assertEquals(23, sut.calculate("(1+(4+5+2)-3)+(6+8)"))

    }

}