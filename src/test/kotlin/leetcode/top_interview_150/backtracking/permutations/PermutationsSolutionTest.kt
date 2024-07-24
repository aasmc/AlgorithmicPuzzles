package leetcode.top_interview_150.backtracking.permutations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PermutationsSolutionTest {

    private val sut = PermutationsSolution()

    @Test
    fun testCorrect() {
        val input = intArrayOf(1, 2, 3)

        val permutations = sut.permute(input)

        permutations.forEach(::println)
    }

}
