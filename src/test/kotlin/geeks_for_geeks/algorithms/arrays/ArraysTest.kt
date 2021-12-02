package geeks_for_geeks.algorithms.arrays

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ArraysTest {

    @Test
    fun insertIntoArray_correctWhenInsertionToTheMiddle() {
        val array = IntArray(10) { -1 }
        val elem = 10
        val index = 3
        array[0] = 1
        array[1] = 2
        array[2] = 3
        array[3] = 4
        array[4] = 5
        val size = 5
        val capacity = 10
        val expected = 6
        assertEquals(expected, insertIntoArray(array, elem, index, size, capacity))
        assertEquals(elem, array[index])
    }

    @Test
    fun insertIntoArray_correctWhenArrayIsFull() {
        val array = IntArray(5) { -1 }
        val elem = 10
        val index = 3
        array[0] = 1
        array[1] = 2
        array[2] = 3
        array[3] = 4
        array[4] = 5
        val size = 5
        val capacity = 5
        val expected = 5
        assertEquals(expected, insertIntoArray(array, elem, index, size, capacity))
        assertEquals(4, array[index])
    }

}



















