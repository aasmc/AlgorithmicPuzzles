package geeks_for_geeks.algorithms.backtracking

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BacktrackingTest {
    @Test
    fun naivePermutations_correct() {
        val expected = listOf("ACB", "BAC", "BCA", "CBA").sorted()
        val result = naivePermutations("ABC", "AB").sorted()
        assertEquals(expected, result)
    }
}