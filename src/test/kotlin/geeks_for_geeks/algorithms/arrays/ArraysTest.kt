package geeks_for_geeks.algorithms.arrays

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ArraysTest {

    private lateinit var arrayOfFive: ArrayList<Int>

    @BeforeEach
    fun setArray() {
        arrayOfFive = ArrayList(5)
        for (i in 0..4) {
            arrayOfFive.add(i + 1)
        }
    }

    @Test
    fun insertIntoArray_correctWhenInsertionToTheMiddle() {
        val capacity = 10
        val array = ArrayList<Int>(capacity)
        val elem = 10
        val index = 3
        for (i in 0..4) {
            array.add(i + 1)
        }
        val size = 5
        val expected = 6
        assertEquals(expected, insertIntoArray(array, elem, index, size, capacity))
        assertEquals(elem, array[index])
    }

    @Test
    fun insertIntoArray_correctWhenArrayIsFull() {
        val capacity = 5
        val array = ArrayList<Int>(capacity)
        val elem = 10
        val index = 3
        for (i in 0..4) {
            array.add(i + 1)
        }
        val size = 5
        val expected = 5
        assertEquals(expected, insertIntoArray(array, elem, index, size, capacity))
        assertEquals(4, array[index])
    }

    @Test
    fun deleteFromArray_elementExists_correct() {
        val actualSize = deleteFromArray(arrayOfFive, 3, 5)
        val expectedSize = 4
        assertEquals(expectedSize, actualSize)

        assertEquals(4, arrayOfFive[2])
    }

    @Test
    fun deleteFromArray_elementNotExists_correct() {
        val actualSize = deleteFromArray(arrayOfFive, 6, 5)
        val expectedSize = 5
        assertEquals(expectedSize, actualSize)
    }

    @Test
    fun secondLargestInArray_throwsOnEmpty() {
        assertThrows(IllegalArgumentException::class.java) { secondLargestElementInArray(intArrayOf()) }
    }

    @Test
    fun secondLargestOInArray_correct_onArrayWithOneElement() {
        assertEquals(0, secondLargestElementInArray(intArrayOf(3)))
    }

    @Test
    fun secondLargestOInArray_correct_onRandomArray() {
        assertEquals(
            5,
            secondLargestElementInArray(intArrayOf(3, 6, 1, 33, 78, 100, 23, 788))
        )
    }

    @Test
    fun secondLargestOInArray_correct_onArrayOfEqualElements() {
        assertEquals(
            -1,
            secondLargestElementInArray(intArrayOf(3, 3, 3, 3, 3, 3, 3, 3))
        )
    }

    @Test
    fun isSorted_throwsOnEmpty() {
        assertThrows(IllegalArgumentException::class.java) {
            checkIfSortedArray(intArrayOf())
        }
    }

    @Test
    fun isSorted_correctOnSingleElementArray() {
        assertTrue(checkIfSortedArray(intArrayOf(3)))
    }

    @Test
    fun isSorted_true_onSortedArray() {
        assertTrue(checkIfSortedArray(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    }

    @Test
    fun isSorted_false_onNotSortedArray() {
        assertFalse(checkIfSortedArray(intArrayOf(1, 2, 3, 4, 5, 6, 8, 7, 9, 10)))
    }

    @Test
    fun isSorted_true_onArrayOfEqualElements() {
        assertTrue(checkIfSortedArray(intArrayOf(3, 3, 3, 3, 3, 3)))
    }

    @Test
    fun reverseArray_correct() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        reverseArray(arr)
        assertEquals(5, arr[0])
        assertEquals(4, arr[1])
        assertEquals(3, arr[2])
        assertEquals(2, arr[3])
        assertEquals(1, arr[4])
    }

    @Test
    fun reverseArray_correctOnSingleElementArray() {
        val arr = intArrayOf(1)
        reverseArray(arr)
        assertEquals(1, arr[0])
    }

    @Test
    fun removeDuplicates_correctOnArrayOfEqualElements() {
        assertEquals(1, removeDuplicatesFromArray(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArray() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(1, 1, 1, 2, 2, 2, 3, 3, 3)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV2() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10, 20, 30, 30, 30, 30)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV3() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10, 10, 10, 20, 30)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV4() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10, 20, 20, 20, 30)))
    }

    @Test
    fun moveZeroes_correct() {
        val arr = intArrayOf(3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 0, 0, 0, 6, 5, 4)
        moveZeroesToTheEndOfArray(arr)
        for (i in 0 until 8) {
            assertTrue(arr[i] != 0)
        }
        for (i in 8 until arr.size) {
            assertTrue(arr[i] == 0)
        }
    }

    @Test
    fun rotateArrayByOne_correct() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        leftRotateArrayByOne(array)
        assertEquals(2, array[0])
        assertEquals(3, array[1])
        assertEquals(4, array[2])
        assertEquals(5, array[3])
        assertEquals(1, array[4])
    }

    @Test
    fun rotateArrayByD_correct() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        leftRotateByD(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

    @Test
    fun rotateArrayByD_using_tmpArray_correct() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        leftRotateUsingTmpArray(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

    @Test
    fun rotateArrayByD_reversal_correct() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        leftRotateReverse(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

    @Test
    fun findLeadersNaive_correct() {
        val expected = listOf<Int>(10, 6, 5, 2)
        val res = findLeadersInArrayNaive(intArrayOf(7, 10, 4, 3, 6, 5, 2))
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }

        val expectedSorted = listOf(30, 20, 10)
        val resSorted = findLeadersInArrayNaive(intArrayOf(30, 20, 10))
        expectedSorted.forEachIndexed { index, i ->
            assertEquals(i, resSorted[index])
        }
    }

    @Test
    fun findLeadersBackwards_correct() {
        val expected = listOf<Int>(2, 5, 6, 10)
        val res = findLeadersInArrayBackwards(intArrayOf(7, 10, 4, 3, 6, 5, 2))
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }

        val expectedSorted = listOf(10, 20, 30)
        val resSorted = findLeadersInArrayBackwards(intArrayOf(30, 20, 10))
        expectedSorted.forEachIndexed { index, i ->
            assertEquals(i, resSorted[index])
        }
    }

    @Test
    fun findMaxDifference_correct() {
        val arr1 = intArrayOf(2, 3, 10, 6, 4, 8, 1)
        val expected1 = 8
        assertEquals(expected1, findMaxDifference(arr1))

        val arr2 = intArrayOf(7, 9, 5, 6, 3, 2)
        val expected2 = 2
        assertEquals(expected2, findMaxDifference(arr2))

        val arr3 = intArrayOf(30, 10, 8, 2)
        val expected3 = -2
        assertEquals(expected3, findMaxDifference(arr3))
    }

    @Test
    fun findFrequenciesInSorted_array() {
        val res1 = findFrequenciesInSortedArray(intArrayOf(4, 5, 5, 5))
        assertEquals(1, res1[4])
        assertEquals(3, res1[5])

        val res2 = findFrequenciesInSortedArray(intArrayOf(5, 5, 5, 5))
        assertEquals(4, res2[5])

        val res3 = findFrequenciesInSortedArray(intArrayOf(1, 2, 3, 4, 5))
        assertEquals(1, res3[1])
        assertEquals(1, res3[2])
        assertEquals(1, res3[3])
        assertEquals(1, res3[4])
        assertEquals(1, res3[5])
    }

    @Test
    fun stockMaxPricesNaive_correct() {
        assertEquals(13, stockMaxProfitNaive(intArrayOf(1, 5, 3, 8, 12)))
    }

    @Test
    fun stockMaxPricesEfficient_correct() {
        assertEquals(13, maxStockPriceEfficient(intArrayOf(1, 5, 3, 8, 12)))
    }

    @Test
    fun tappingWaterNaive_correct() {
        val input1 = intArrayOf(2, 0, 2)
        assertEquals(2, tappingWaterNaive(input1))
        val input2 = intArrayOf(3, 0, 1, 2, 5)
        assertEquals(6, tappingWaterNaive(input2))
        assertEquals(0, tappingWaterNaive(intArrayOf(1, 2, 3)))
        assertEquals(0, tappingWaterNaive(intArrayOf(3, 2, 1)))
    }

    @Test
    fun tappingWaterEfficient_correct() {
        val input1 = intArrayOf(2, 0, 2)
        assertEquals(2, tappingWaterEfficient(input1))
        val input2 = intArrayOf(3, 0, 1, 2, 5)
        assertEquals(6, tappingWaterEfficient(input2))
        assertEquals(0, tappingWaterEfficient(intArrayOf(1, 2, 3)))
        assertEquals(0, tappingWaterEfficient(intArrayOf(3, 2, 1)))
    }

    @Test
    fun maxConsecutiveOnes_correct() {
        assertEquals(0, maxConsecutiveOnes(intArrayOf(0, 0, 0, 0, 0)))
        assertEquals(5, maxConsecutiveOnes(intArrayOf(1, 1, 1, 1, 1)))
        assertEquals(4, maxConsecutiveOnes(intArrayOf(1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1)))
    }

    @Test
    fun maxSubarraySum_correct() {
        assertEquals(11, maxSubarraySum(intArrayOf(2, 3, -8, 7, -1, 2, 3)))
        assertEquals(16, maxSubarraySum(intArrayOf(5, 8, 3)))
        assertEquals(-1, maxSubarraySum(intArrayOf(-6, -1, -8)))
        assertEquals(4, maxSubarraySum(intArrayOf(-5, 1, -2, 3, -1, 2, -2)))
    }

    @Test
    fun maxLengthEvenOddSubarrayNaive_correct() {
        assertEquals(3, maxLengthEvenOddSubarrayNaive(intArrayOf(10, 12, 14, 7, 8)))
        assertEquals(4, maxLengthEvenOddSubarrayNaive(intArrayOf(7, 10, 9, 14)))
        assertEquals(1, maxLengthEvenOddSubarrayNaive(intArrayOf(10, 12, 8, 4)))
    }

    @Test
    fun maxLengthEvenOddSubarrayEfficient_correct() {
        assertEquals(3, maxLengthEvenOddSubarrayEfficient(intArrayOf(10, 12, 14, 7, 8)))
        assertEquals(4, maxLengthEvenOddSubarrayEfficient(intArrayOf(7, 10, 9, 14)))
        assertEquals(1, maxLengthEvenOddSubarrayEfficient(intArrayOf(10, 12, 8, 4)))
    }

    @Test
    fun maxSumOfCircularSubarray_correct() {
        assertEquals(15, maxSumOfCircularSubarrayNaive(intArrayOf(10, 5, -5)))
        assertEquals(12, maxSumOfCircularSubarrayNaive(intArrayOf(5, -2, 3, 4)))
        assertEquals(5, maxSumOfCircularSubarrayNaive(intArrayOf(2, 3, -4)))
        assertEquals(12, maxSumOfCircularSubarrayNaive(intArrayOf(8, -4, 3, -5, 4)))
        assertEquals(10, maxSumOfCircularSubarrayNaive(intArrayOf(-3, 4, 6, -2)))
    }

    @Test
    fun maxSumOfCircularSubarrayEfficient_correct() {
        assertEquals(15, maxSumOfCircularSubarrayEfficient(intArrayOf(10, 5, -5)))
        assertEquals(12, maxSumOfCircularSubarrayEfficient(intArrayOf(5, -2, 3, 4)))
        assertEquals(5, maxSumOfCircularSubarrayEfficient(intArrayOf(2, 3, -4)))
        assertEquals(12, maxSumOfCircularSubarrayEfficient(intArrayOf(8, -4, 3, -5, 4)))
        assertEquals(10, maxSumOfCircularSubarrayEfficient(intArrayOf(-3, 4, 6, -2)))
    }

    @Test
    fun majorityElement_correct() {
        val res = majorityElementNaive(intArrayOf(8,3,4,8,8))
        val check = res == 0 || res == 3 || res == 4
        assertTrue(check)

        assertEquals(-1, majorityElementNaive(intArrayOf(1,2,3,4,7,7,5)))
    }

    @Test
    fun majorityElementEfficient_correct() {
        val res = majorityElementEfficient(intArrayOf(8,3,4,8,8))
        val check = res == 0 || res == 3 || res == 4
        assertTrue(check)

        assertEquals(-1, majorityElementEfficient(intArrayOf(1,2,3,4,7,7,5)))
    }

}



















