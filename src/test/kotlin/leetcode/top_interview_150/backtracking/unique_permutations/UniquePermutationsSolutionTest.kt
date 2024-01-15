package leetcode.top_interview_150.backtracking.unique_permutations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UniquePermutationsSolutionTest {

    private val sut = UniquePermutationsSolution()

    @Test
    fun testCorrect() {
        val in1 = intArrayOf(1,1,2)
        val permutations = sut.permuteUnique(in1)
        assertEquals(3, permutations.size)
        permutations.forEach { permutation ->
            println(permutation)
        }
    }

}