package geeks_for_geeks.algorithms.sorting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GFGSortingTest {

    @Test
    fun bubbleSort_correct() {
        val arr = intArrayOf(5, 2, 7, 1, 3, 9, 10)
        bubbleSort(arr)
        for (i in 0 until arr.lastIndex - 1) {
            assertTrue(arr[i] <= arr[i + 1])
        }
    }

    @Test
    fun selectionSort_correct() {
        val arr = intArrayOf(5, 2, 7, 1, 3, 9, 10)
        selectionSort(arr)
        for (i in 0 until arr.lastIndex - 1) {
            assertTrue(arr[i] <= arr[i + 1])
        }
    }

    @Test
    fun insertionSort_correct() {
        val arr = intArrayOf(5, 2, 7, 1, 3, 9, 10)
        insertionSort(arr)
        for (i in 0 until arr.lastIndex - 1) {
            assertTrue(arr[i] <= arr[i + 1])
        }
    }

    @Test
    fun mergeSortedArrays_correct() {
        val first = intArrayOf(1, 3, 5, 7, 9)
        val second = intArrayOf(2, 4, 6, 8, 10)
        val result = mergeSortedArrays(first, second)
        assert(result.size == 10)
        for (i in 0 until result.lastIndex) {
            assertTrue(result[i] <= result[i + 1])
        }
    }

    @Test
    fun merge_correct() {
        val first = intArrayOf(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
        merge(first, 0, 4, 9)
        for (i in 0 until first.lastIndex) {
            assertTrue(first[i] <= first[i + 1])
        }
    }

    @Test
    fun mergeSort_correct() {
        val arr = intArrayOf(5, 2, 7, 1, 3, 9, 10)
        mergeSort(arr)
        for (i in 0 until arr.lastIndex - 1) {
            assertTrue(arr[i] <= arr[i + 1])
        }
    }

    @Test
    fun intersectionOfTwoSortedArrays_correct() {
        var first = intArrayOf(3,5,10,10,10,15,15,20)
        var second = intArrayOf(5,10,10,15,25)
        var res = intersectionOfTwoSortedArrays(first, second)
        assertEquals(3, res.size)
        assertEquals(5, res[0])
        assertEquals(10, res[1])
        assertEquals(15, res[2])

        first = intArrayOf(1,1,3,3,3,3)
        second = intArrayOf(1,1,1,1,1,1,1,3,5,7)
        res = intersectionOfTwoSortedArrays(first, second)
        assertEquals(2, res.size)
        assertEquals(1, res[0])
        assertEquals(3, res[1])
    }
}






















