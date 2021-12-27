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

    @Test
    fun firstOccurrenceInSortedArray_correct() {
        val input = intArrayOf(10, 10, 10, 20, 30, 40, 40, 40, 50)
        var res = firstOccurrenceInSortedArray(input, 40)
        assertEquals(5, res)
        res = firstOccurrenceInSortedArray(input, 5)
        assertEquals(-1, res)
        res = firstOccurrenceInSortedArray(input, 10)
        assertEquals(0, res)
        assertEquals(0, firstOccurrenceInSortedArray(intArrayOf(5,5,5,5,5,5,5,5), 5))
    }

    @Test
    fun lastOccurrenceInSortedArray_correct() {
        val input = intArrayOf(1,1,1,1,1,1,3,3,3,3,3,4,4,4,4,6,6,6,6,7,8,9,10)
        var res = lastOccurrenceInSortedArray(input, 1)
        assertEquals(5, res)
        res = lastOccurrenceInSortedArray(input, 10)
        assertEquals(input.lastIndex, res)
        res = lastOccurrenceInSortedArray(input, -1)
        assertEquals(-1, res)
        res = lastOccurrenceInSortedArray(input, 8)
        assertEquals(input.lastIndex - 2, res)
    }

    @Test
    fun countOccurrencesInSortedArray_correct() {
        val input = intArrayOf(1,1,1,2,2,2,2,2,3,3,3,4,4,5,6,7,8,9,9)
        var res = countOccurrencesInSortedArray(input, -1)
        assertEquals(0, res)
        res = countOccurrencesInSortedArray(input, 1)
        assertEquals(3, res)
        res = countOccurrencesInSortedArray(input, 2)
        assertEquals(5, res)
        res = countOccurrencesInSortedArray(input, 8)
        assertEquals(1, res)
        res = countOccurrencesInSortedArray(input, 9)
        assertEquals(2, res)
    }

    @Test
    fun countOccurrencesInSortedArrayEfficient_correct() {
        val input = intArrayOf(1,1,1,2,2,2,2,2,3,3,3,4,4,5,6,7,8,9,9)
        var res = countOccurrencesInSortedArrayEfficient(input, -1)
        assertEquals(0, res)
        res = countOccurrencesInSortedArrayEfficient(input, 1)
        assertEquals(3, res)
        res = countOccurrencesInSortedArrayEfficient(input, 2)
        assertEquals(5, res)
        res = countOccurrencesInSortedArrayEfficient(input, 8)
        assertEquals(1, res)
        res = countOccurrencesInSortedArrayEfficient(input, 9)
        assertEquals(2, res)
    }
}

























