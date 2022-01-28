package geeks_for_geeks.algorithms.sorting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GFGSortingTest {

    @Test
    fun bubbleSort_correct() {
        val arr = intArrayOf(5,2,7,1,3,9,10)
        bubbleSort(arr)
        for (i in 0 until arr.lastIndex - 1) {
            assertTrue(arr[i] <= arr[i + 1])
        }
    }
}