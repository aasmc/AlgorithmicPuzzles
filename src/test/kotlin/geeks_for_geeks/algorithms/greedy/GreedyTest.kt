package geeks_for_geeks.algorithms.greedy

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GreedyTest {
    @Test
    fun activitySelection_correct() {
        val expected = listOf<IntRange>(
            1..3,
            4..8,
            10..11
        )
        val result = activitySelection(listOf(4..8, 2..4, 1..3, 10..11))
        assertEquals(expected, result)
    }
}