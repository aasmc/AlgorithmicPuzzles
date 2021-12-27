package geeks_for_geeks.algorithms.searching

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LecturesKtTest {

    @Test
    fun binarySearch_anyIndex_correct() {
        assertEquals(2, binarySearch(intArrayOf(10, 20, 30, 40, 50), 30))
        assertEquals(-1, binarySearch(intArrayOf(10, 20, 30, 40, 50), 60))
        assertEquals(0, binarySearch(intArrayOf(10, 20, 30, 40, 50), 10))
        val res = binarySearch(intArrayOf(10, 10), 10)
        assertTrue(res == 0 || res == 1)
    }

    @Test
    fun binarySearchRecursive_anyIndex_correct() {
        assertEquals(2, binarySearchRecursive(intArrayOf(10, 20, 30, 40, 50), 30))
        assertEquals(-1, binarySearchRecursive(intArrayOf(10, 20, 30, 40, 50), 60))
        assertEquals(0, binarySearchRecursive(intArrayOf(10, 20, 30, 40, 50), 10))
        val res = binarySearchRecursive(intArrayOf(10, 10), 10)
        assertTrue(res == 0 || res == 1)
    }
}