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

    @Test
    fun countOnesInBinarySortedArray_correct() {
        val input = intArrayOf(0,0,0,0,0,0,1,1,1,1,1,1)
        assertEquals(6, countOnesInBinarySortedArray(input))
        val input2 = intArrayOf(0,0,0,0,0)
        assertEquals(0, countOnesInBinarySortedArray(input2))
    }

    @Test
    fun squareRootFloorNaive_corect() {
        assertEquals(2, squareRootFloorNaive(4))
        assertEquals(3, squareRootFloorNaive(9))
        assertEquals(3, squareRootFloorNaive(14))
        assertEquals(5, squareRootFloorNaive(25))
    }

    @Test
    fun squareRootFloorEfficient_squareRootFloorEfficient() {
        assertEquals(2, squareRootFloorEfficient(4))
        assertEquals(3, squareRootFloorEfficient(9))
        assertEquals(3, squareRootFloorEfficient(14))
        assertEquals(5, squareRootFloorEfficient(25))
    }

    @Test
    fun searchInInfiniteArray_correct() {
        val input = intArrayOf(1,2,3,4,5,10,20,30,40,100,200,300,400,500)
        var target = 2
        assertEquals(1, searchInInfiniteArray(input, target))
        target = 700
        assertEquals(-1, searchInInfiniteArray(input, target))
        target = 10
        assertEquals(5, searchInInfiniteArray(input, target))
    }

    @Test
    fun searchInSortedRotatedArray_correct(){
        val input = intArrayOf(30, 40, 50, 60, 70, 80, 10, 20)
        var target = 100
        assertEquals(-1, searchInSortedRotatedArray(input, target))
        target = 30
        assertEquals(0, searchInSortedRotatedArray(input, target))
        target = 20
        assertEquals(input.lastIndex, searchInSortedRotatedArray(input, target))
        target = 10
        assertEquals(input.lastIndex - 1, searchInSortedRotatedArray(input, target))
    }

    @Test
    fun findPeakElementInArray_correct() {
        val middlePeak = intArrayOf(5, 10, 20, 15, 7)
        val out = findPeakElementInArray(middlePeak)
        assertEquals(20, out)

        val severalPeakElements = intArrayOf(10, 20, 15, 7, 35, 18)
        val res = findPeakElementInArray(severalPeakElements)
        assertTrue(res == 20 || res == 35)


        val firstPeak = intArrayOf(80, 70, 60)
        val firstPeakRes = findPeakElementInArray(firstPeak)
        assertEquals(80, firstPeakRes)
    }

    @Test
    fun findPairWithSumEqualToX_correct() {
        val input1 = intArrayOf(3,5,9,2,8,10,11)
        var res = findPairWithSumEqualToXInUnsortedArray(input1, 17)
        assertTrue(res.first == 2 || res.second == 2)
        assertTrue(res.second == 4 || res.first == 4)
        val input2 = intArrayOf(8,4,6)
        res = findPairWithSumEqualToXInUnsortedArray(input2, 11)
        assertEquals(-1, res.first)
        assertEquals(-1, res.second)
    }

    @Test
    fun findPairWithSumEqualToXSortedArray_correct() {
        val input1 = intArrayOf(2,5,8,12,30)
        var res = findPairWithSumEqualToXInSortedArray(input1, 17)
        assertTrue(res.first == 1 || res.second == 1)
        assertTrue(res.second == 3 || res.first == 3)
        val input2 = intArrayOf(4, 6, 8)
        res = findPairWithSumEqualToXInSortedArray(input2, 11)
        assertEquals(-1, res.first)
        assertEquals(-1, res.second)
    }

    @Test
    fun findTripleWithSumSortedArray_correct() {
        val input = intArrayOf(2,3,4,8,9,20,40)
        val res = findTripletWithSumInSortedArray(input, 32)
        assertEquals(1, res.first)
        assertEquals(4, res.second)
        assertEquals(5, res.third)
    }

    @Test
    fun findMedianOfTwoSortedArrays_correct() {
        var first = intArrayOf(10, 20, 30, 40, 50)
        var second = intArrayOf(5, 15, 25, 35, 45)
        var result = findMedianOfTwoSortedArrays(first, second)
        assertTrue(result.equalsDelta(27.5))

        first = intArrayOf(1,2,3,4,5,6)
        second = intArrayOf(10, 20, 30, 40, 50)
        result = findMedianOfTwoSortedArrays(first, second)
        assertTrue(result.equalsDelta(6.0))

        first = intArrayOf(10, 20, 30, 40, 50, 60)
        second = intArrayOf(1,2,3,4,5)
        result = findMedianOfTwoSortedArrays(first, second)
        assertTrue(result.equalsDelta(10.0))
    }

    @Test
    fun findRepeatingElement_correct() {
        var input = intArrayOf(0, 3, 2, 4, 5, 2, 2, 6, 1)
        var res = findRepeatingElement(input)
        assertEquals(2, res)

        input = intArrayOf(0, 0)
        res = findRepeatingElement(input)
        assertEquals(0, res)
    }

    @Test
    fun allocateMinimumPagesRecursive_correct() {
        var books = intArrayOf(10, 20, 30, 40)
        var students = 2
        var res = allocateMinimumNumberOfPagesRecursive(books, students)
        assertEquals(60, res)
        books = intArrayOf(10, 20, 30)
        students = 1
        res = allocateMinimumNumberOfPagesRecursive(books, students)
        assertEquals(60, res)
        books = intArrayOf(10, 5, 30, 1, 2, 5, 10, 10)
        students = 3
        res = allocateMinimumNumberOfPagesRecursive(books, students)
        assertEquals(30, res)
    }
    @Test
    fun allocateMinimumPages_correct() {
        var books = intArrayOf(10, 20, 30, 40)
        var students = 2
        var res = allocateMinimumNumberOfPages(books, students)
        assertEquals(60, res)
        books = intArrayOf(10, 20, 30)
        students = 1
        res = allocateMinimumNumberOfPages(books, students)
        assertEquals(60, res)
        books = intArrayOf(10, 5, 30, 1, 2, 5, 10, 10)
        students = 3
        res = allocateMinimumNumberOfPages(books, students)
        assertEquals(30, res)
    }

    @Test
    fun findLastOccurrenceInArraySortedDecreasing_correct() {
        var arr = intArrayOf(1,1,1,1,1,0,0,0)
        assertEquals(4, findLastOccurrenceInArraySortedDecreasing(arr, 1))
        arr = intArrayOf(1,1,1,0,0,0,0,)
        assertEquals(2, findLastOccurrenceInArraySortedDecreasing(arr, 1))
        arr = intArrayOf(1,1,1)
        assertEquals(2, findLastOccurrenceInArraySortedDecreasing(arr, 1))
    }

    @Test
    fun findFloor_correct() {
        var input = intArrayOf(1,2,8,10,11,12,19)
        assertEquals(-1, findFloor(input, 0))
        input = intArrayOf(1,2,8,10,11,12,19)
        assertEquals(1, findFloor(input, 5))
        input = intArrayOf(26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70 ,71, 72 ,73, 74, 75 ,76 ,77 ,78 ,79 ,80 ,81 ,82 ,83, 84 ,85 ,86 ,87 ,88 ,89 ,90 ,91 ,92 ,93 ,94 ,95 ,96 ,97 ,98 ,99 ,100 ,101 ,102 ,103 ,104, 105, 106 ,107)
        assertEquals(2, findFloor(input, 28))
    }

    @Test
    fun findMinNumber_correct() {
        var input = intArrayOf(2,3,4,5,6,7,8,9,10,1)
        assertEquals(1, minNumber(input))
        input = intArrayOf(3,4,5,1,2)
        assertEquals(1, minNumber(input))
        input = intArrayOf(6,7,1,2,3,4,5)
        assertEquals(1, minNumber(input))
        input = intArrayOf(34, 7, 18 ,26)
        assertEquals(7, minNumber(input))
        input = intArrayOf(6 ,11, 24 ,37 ,42)
        assertEquals(6, minNumber(input))
    }

    @Test
    fun findTwoRepeatedElements_correct() {
        val input = intArrayOf(1, 2 ,6 ,3 ,4 ,5 ,6 ,4)
        val res = findTwoRepeatedElements(input)
        assertTrue(res.first == 6 || res.first == 4)
        assertTrue(res.second == 6 || res.second == 4)
    }

}

























