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

}



















