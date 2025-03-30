package leetcode.medium.backtracking.palindrome_partitioning

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PalindromePartitioningSolutionTest {

    private val sut = PalindromePartitioningSolution()

    @Test
    fun testCorrect() {

        val res = sut.partition("aab")
        val ex = listOf(listOf("a", "a", "b"), listOf("aa", "b"))
        assertEquals(ex, res)

    }

}