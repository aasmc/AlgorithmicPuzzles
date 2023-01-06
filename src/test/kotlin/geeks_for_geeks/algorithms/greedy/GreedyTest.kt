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

    @Test
    fun fractionalKnapsack_correct() {
        val expected = 1140.0
        val result = fractionalKnapsack(
            70.0,
            listOf(
                KnapsackItem(600.0, 50.0),
                KnapsackItem(500.0, 20.0),
                KnapsackItem(400.0, 30.0),
            )
        )
        assertEquals(expected, result, 0.00001)
    }
}