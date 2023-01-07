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

    @Test
    fun jobSequencing_correct() {
        val r1 = jobSequencing(listOf(
            JobSequence(4, 70.0),
            JobSequence(1, 80.0),
            JobSequence(1, 30.0),
            JobSequence(1, 100.0),
        ))
        val ex1 = 170.0
        assertEquals(ex1, r1, 0.0001)

        val r2 = jobSequencing(listOf(
            JobSequence(2, 50.0),
            JobSequence(2, 60.0),
            JobSequence(3, 20.0),
            JobSequence(3, 30.0),
        ))
        val ex2 = 140.0
        assertEquals(ex2, r2, 0.0001)

        val r3 = jobSequencing(listOf(
            JobSequence(2, 100.0),
            JobSequence(1, 50.0),
            JobSequence(2, 10.0),
            JobSequence(1, 20.0),
            JobSequence(3, 30.0),
        ))
        val ex3 = 180.0
        assertEquals(ex3, r3, 0.0001)

    }
}
































