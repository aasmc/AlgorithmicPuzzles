package tinkoff.three

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IsErrorTest {

    @Test
    fun isErrorTest() {
        assertTrue(isError(intArrayOf(3,4,4,3), Period(0, 3)))
        assertTrue(isError(intArrayOf(3,4), Period(0, 1)))
        assertTrue(isError(intArrayOf(4,4), Period(0, 1)))
        assertTrue(isError(intArrayOf(4,3), Period(0, 1)))
        assertTrue(isError(intArrayOf(1,2,3,4,5,5,5,5,5,5,6,6), Period(0, 11)))
        assertTrue(isError(intArrayOf(1,2,3,4,5,5,5,5,5,5,6,6,4,4,2,1), Period(0, 15)))
        assertFalse(isError(intArrayOf(1,2,3,4,3,2,4), Period(0, 6)))
        assertFalse(isError(intArrayOf(4,5,4,5), Period(0, 3)))
        assertFalse(isError(intArrayOf(4,4,4,4,5,5,5,5,6,6,6,6,1,1,1,1,2), Period(0, 16)))
        assertFalse(isError(intArrayOf(1,0,1), Period(0, 2)))
    }

}