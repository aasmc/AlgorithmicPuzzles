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
        assertEquals(5,
            secondLargestElementInArray(intArrayOf(3, 6, 1, 33, 78, 100, 23, 788)))
    }

    @Test
    fun secondLargestOInArray_correct_onArrayOfEqualElements() {
        assertEquals(-1,
            secondLargestElementInArray(intArrayOf(3,3,3,3,3,3,3,3)))
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
        assertTrue(checkIfSortedArray(intArrayOf(1,2,3,4,5,6,7,8,9,10)))
    }

    @Test
    fun isSorted_false_onNotSortedArray() {
        assertFalse(checkIfSortedArray(intArrayOf(1,2,3,4,5,6,8,7,9,10)))
    }

    @Test
    fun isSorted_true_onArrayOfEqualElements() {
        assertTrue(checkIfSortedArray(intArrayOf(3,3,3,3,3,3)))
    }

    @Test
    fun reverseArray_correct() {
        val arr = intArrayOf(1,2,3,4,5)
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
        assertEquals(1, removeDuplicatesFromArray(intArrayOf(1,1,1,1,1,1,1,1,1)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArray() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(1,1,1,2,2,2,3,3,3)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV2() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10,20,30,30,30,30)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV3() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10,10,10,20,30)))
    }

    @Test
    fun removeDuplicates_correctOnRandomSortedArrayV4() {
        assertEquals(3, removeDuplicatesFromArray(intArrayOf(10,20, 20, 20,30)))
    }

    @Test
    fun moveZeroes_correct() {
        val arr = intArrayOf(3,0,4,0,5,0,6,0,7,0,0,0,0,6,5,4)
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
        val array = intArrayOf(1,2,3,4,5)
        leftRotateArrayByOne(array)
        assertEquals(2, array[0])
        assertEquals(3, array[1])
        assertEquals(4, array[2])
        assertEquals(5, array[3])
        assertEquals(1, array[4])
    }

    @Test
    fun rotateArrayByD_correct() {
        val array = intArrayOf(1,2,3,4,5)
        leftRotateByD(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

    @Test
    fun rotateArrayByD_using_tmpArray_correct() {
        val array = intArrayOf(1,2,3,4,5)
        leftRotateUsingTmpArray(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

    @Test
    fun rotateArrayByD_reversal_correct() {
        val array = intArrayOf(1,2,3,4,5)
        leftRotateReverse(array, 3)
        assertEquals(4, array[0])
        assertEquals(5, array[1])
        assertEquals(1, array[2])
        assertEquals(2, array[3])
        assertEquals(3, array[4])
    }

}



















