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
        var first = intArrayOf(3, 5, 10, 10, 10, 15, 15, 20)
        var second = intArrayOf(5, 10, 10, 15, 25)
        var res = intersectionOfTwoSortedArrays(first, second)
        assertEquals(3, res.size)
        assertEquals(5, res[0])
        assertEquals(10, res[1])
        assertEquals(15, res[2])

        first = intArrayOf(1, 1, 3, 3, 3, 3)
        second = intArrayOf(1, 1, 1, 1, 1, 1, 1, 3, 5, 7)
        res = intersectionOfTwoSortedArrays(first, second)
        assertEquals(2, res.size)
        assertEquals(1, res[0])
        assertEquals(3, res[1])
    }

    @Test
    fun unionOfTwoSortedArrays_correct() {
        val first = intArrayOf(2, 3, 3, 3, 4, 4, 4, 5)
        val second = intArrayOf(3, 3, 3, 3, 3, 4, 4, 4, 4)
        val result = unionOfTwoSortedArrays(first, second)
        assertEquals(4, result.size)
        for (i in 0 until result.lastIndex) {
            assertTrue(result[i] < result[i + 1])
        }
    }

    @Test
    fun countInversions_correct() {
        val sorted = intArrayOf(1, 2, 3, 4, 5, 6)
        assertEquals(0, countInversions(sorted))
        val sortedDecreasing = intArrayOf(6, 5, 4, 3, 2, 1)
        assertEquals(
            sortedDecreasing.size * (sortedDecreasing.size - 1) / 2,
            countInversions(sortedDecreasing)
        )

        val rand = intArrayOf(3, 2, 5, 1, 6, 4, 3, 8)
        assertEquals(9, countInversions(rand))
    }

    @Test
    fun partitionStable_correct() {
        val input = intArrayOf(5, 3, 12, 8, 5)
        val pivotIdx = 4
        assertEquals(2, partitionStable(input, 0, 4, 4))
    }

    @Test
    fun lomutoPartition_correct() {
        val input = intArrayOf(5, 3, 12, 8, 5)
        assertEquals(2, lomutoPartition(input, 0, 4, 0))
    }

    @Test
    fun hoarePartition_correct() {
        val input = intArrayOf(5, 3, 12, 8, 5)
        val pivotIdx = 4
        val pivot = input[pivotIdx]
        val res = hoarePartition(input, 0, 4, pivotIdx)
        for (i in 0 until res) {
            assertTrue(input[i] <= pivot)
        }
    }

    @Test
    fun quickSortLomuto_correct() {
        val input = intArrayOf(3, 2, 14, 56, 73, 12, 809, 135, 665)
        quickSortLomuto(input)
        for (i in 0 until input.lastIndex) {
            assertTrue(input[i] <= input[i + 1])
        }
    }

    @Test
    fun quickSortHoare_correct() {
        val input = intArrayOf(3, 2, 14, 56, 73, 12, 809, 135, 665)
        quickSortHoare(input)
        for (i in 0 until input.lastIndex) {
            assertTrue(input[i] <= input[i + 1])
        }
    }

    @Test
    fun kThSmallestElement_correct() {
        var input = intArrayOf(10, 5, 30, 12)
        var k = 2
        var res = kThSmallestElement(input, k)
        assertEquals(10, res)
        input = intArrayOf(30, 20, 5, 10, 8)
        k = 4
        res = kThSmallestElement(input, k)
        assertEquals(20, res)
    }

    @Test
    fun minimumDifference_correct() {
        val input = intArrayOf(7, 3, 2, 4, 9, 12, 56)
        val res = minimumDifference(input, 3)
        assertEquals(2, res)
    }

    @Test
    fun segregateArrayByPredicate_correct() {
        val input = intArrayOf(-12, 18, -10, 15)
        segregateArrayByPredicate(input) { elem ->
            elem >= 0
        }
        assertTrue(input[0] < 0)
        assertTrue(input[1] < 0)
        assertTrue(input[2] >= 0)
        assertTrue(input[3] >= 0)
    }

    @Test
    fun segregateArrayByTwoPredicates_correct() {
        val input = intArrayOf(0, 1, 2, 1, 1, 2)
        val firstPredicate = { elem: Int ->
            elem == 0
        }
        val secondPredicate = { elem: Int ->
            elem == 2
        }
        segregateArrayByTwoPredicates(input, firstPredicate, secondPredicate)
        assertTrue(input[0] == 0)
        assertTrue(input[1] == 1)
        assertTrue(input[2] == 1)
        assertTrue(input[3] == 1)
        assertTrue(input[4] == 2)
        assertTrue(input[5] == 2)
    }

    @Test
    fun minDiffInArray_correct() {
        val input = intArrayOf(8, -1, 0, 3)
        assertEquals(1, minDiffInArray(input))
    }

    @Test
    fun mergeOverlappingIntervals_correct() {
        val input = mutableListOf(
            Interval(7, 9),
            Interval(6, 10),
            Interval(4, 5),
            Interval(1, 3),
            Interval(2, 4),
        )
        val res = listOf(
            Interval(1, 5),
            Interval(6, 10),
        )
        assertEquals(res, mergeOverlappingIntervals(input))
    }

    @Test
    fun countMaxNumberOfGuests_correct() {
        val arrivals = intArrayOf(900, 600, 700)
        val departures = intArrayOf(1000, 800, 730)
        assertEquals(2, countMaxNumberOfGuests(arrivals, departures))
    }

    @Test
    fun cycleSortNoDuplicates_correct() {
        val initial = intArrayOf(20, 40, 30, 70, 40, 50, 60)
        cycleSortNoDuplicates(initial)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }

    @Test
    fun heapSort_correct() {
        val initial = intArrayOf(20, 40, 30, 70, 40, 50, 60)
        heapSort(initial)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }

    @Test
    fun countingSortNaive_correct() {
        val initial = intArrayOf(5, 4, 3, 2, 1)
        countingSortNaive(initial, 6)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }

    @Test
    fun countingSortEfficient_correct() {
        val initial = intArrayOf(5, 4, 3, 2, 1)
        countingSortEfficient(initial, 6)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }

    @Test
    fun radixSort_correct() {
        val initial = intArrayOf(319, 212, 6, 8, 100, 50)
        radixSort(initial)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }


    @Test
    fun bucketSort_correct() {
        val initial = intArrayOf(30,40,10,80,5,12,70)
        val bucketsNum = 4
        bucketSort(initial, bucketsNum)
        for (i in 0 until initial.lastIndex) {
            assertTrue(initial[i] <= initial[i + 1])
        }
    }

    @Test
    fun findTriangles_correct() {
        val i1 = intArrayOf(3,5,4)
        assertEquals(1, findNumberOfTriangles(i1))
        val i2 = intArrayOf(6, 4, 9, 7, 8)
        assertEquals(10, findNumberOfTriangles(i2))
    }

    @Test
    fun findTriplets_correct() {
        val input = intArrayOf(4, -16, 43, 4, 7, -36, 18)
        assertFalse(findTriplets(input))
    }
}






















