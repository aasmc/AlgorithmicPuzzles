package geeks_for_geeks.algorithms.bitwise_operations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BitwiseOperationsLecturesTest {

    @Test
    fun setBit_correct() {
        val expected = 0b1100
        assertEquals(expected, setBit(0b1000, 2))
    }

    @Test
    fun isSet_correct() {
        assertTrue(isSet(0b1000, 3))
    }

    @Test
    fun clearBit_correct() {
        val expected = 0b0111
        assertEquals(expected, clearBit(0b1111, 3))
    }

    @Test
    fun toggleBit_one_to_zero() {
        val expected = 0b1011
        assertEquals(expected, toggleBit(0b1111, 2))
    }

    @Test
    fun toggleBit_zero_to_one() {
        val expected = 0b1111
        assertEquals(expected, toggleBit(0b1011, 2))
    }

    @Test
    fun setAllCorrect() {
        val expected = 0b1111
        assertEquals(expected, setAll(4))
    }

    @Test
    fun isPowerOfTwo_returnsTrueOnPowerOfTwo() {
        assertTrue(isPowerOfTwo(16))
    }

    @Test
    fun isPowerOfTwo_returnsFalseNotOnPowerOfTwo() {
        assertFalse(isPowerOfTwo(15))
    }
}


















