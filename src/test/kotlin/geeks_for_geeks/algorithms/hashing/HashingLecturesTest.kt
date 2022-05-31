package geeks_for_geeks.algorithms.hashing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HashingLecturesTest {
    @Test
    fun countDistinctElementsCorrect() {
        assertEquals(4, countDistinctElementsIn(intArrayOf(15,12,12,13,16,16,15)))
        assertEquals(1, countDistinctElementsIn(intArrayOf(15,15,15,15,15,15)))
        assertEquals(3, countDistinctElementsIn(intArrayOf(1,2,3)))
    }
}