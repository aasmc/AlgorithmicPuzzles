package leetcode.ace_coding.dynamic_programming.edit_distance

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EditDistanceSolutionTest {

    private val sut = EditDistanceSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.minDistance("horse", "ros"))
    }

}