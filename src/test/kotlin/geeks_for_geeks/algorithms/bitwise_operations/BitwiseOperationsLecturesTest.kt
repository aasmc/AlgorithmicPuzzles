package geeks_for_geeks.algorithms.bitwise_operations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class BitwiseOperationsLecturesTest {

    @Test
    fun setBit_correct() {
        val expected = 0b1100
        assertEquals(expected, setBit(0b1000, 3))
    }

    @Test
    fun isSetByShl_correct() {
        assertTrue(isSetByShl(0b1000, 4))
    }

    @Test
    fun isSetByShl_correct_on_39_5() {
        assertFalse(isSetByShl(39, 5))
    }

    @Test
    fun isSetByShr_correct() {
        assertTrue(isSetByShr(0b1000, 4))
    }

    @Test
    fun clearBit_correct() {
        val expected = 0b0111
        assertEquals(expected, clearBit(0b1111, 4))
    }

    @Test
    fun toggleBit_one_to_zero() {
        val expected = 0b1011
        assertEquals(expected, toggleBit(0b1111, 3))
    }

    @Test
    fun toggleBit_zero_to_one() {
        val expected = 0b1111
        assertEquals(expected, toggleBit(0b1011, 3))
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

    @Test
    fun countSetBitsNaive_correct() {
        assertEquals(2, countSetBitsNaive(5))
        assertEquals(3, countSetBitsNaive(7))
        assertEquals(3, countSetBitsNaive(13))
    }

    @Test
    fun countSetBitsKerningam_correct() {
        assertEquals(2, countSetBitsKerningam(5))
        assertEquals(3, countSetBitsKerningam(7))
        assertEquals(3, countSetBitsKerningam(13))
        assertEquals(2, countSetBitsKerningam(40))
    }

    @Test
    fun countSetBitsLookup_correct() {
        initializeTable()
        assertEquals(2, countSetBitsLookupTable(5))
        assertEquals(3, countSetBitsLookupTable(7))
        assertEquals(3, countSetBitsLookupTable(13))
        assertEquals(2, countSetBitsLookupTable(40))
    }

    @Test
    fun oneOddOccurring_correct() {
        var expected = 3
        var input = intArrayOf(4, 3, 4, 4, 4, 5, 5)
        assertEquals(expected, oneOddOccurring(input))

        expected = 8
        input = intArrayOf(8, 7, 7, 8, 8)
        assertEquals(expected, oneOddOccurring(input))
    }

    @Test
    fun findOneMissingElement() {
        val expected = 3
        val input = intArrayOf(1, 2, 4)
        assertEquals(expected, findOneMissingNumberInArray(input))
    }

    @Test
    fun findTwoOddOccurrencesInArray_correct() {
        val input = intArrayOf(3, 4, 3, 4, 5, 4, 4, 6, 7, 7)
        val expected = intArrayOf(5, 6)
        val res = findTwoOddOccurrencesInArray(input)
        assertTrue(expected.size == res.size)
        res.forEach {
            assertTrue(expected.contains(it))
        }
    }

    @Test
    fun getLastSetBitCorrect() {
        assertEquals(0b1000, getLastSetBit(0b110011000))
    }

    @Test
    fun getLastSetBitCorrect6636() {
        assertEquals(0b100, getLastSetBit(6636))
    }

    @Test
    fun generatePowerSet_correct() {
        val expected = listOf<String>("", "a", "b", "c", "ab", "ac", "bc", "abc")
        val result = generatePowerSet("abc")
        assertEquals(expected.size, result.size)
        assertTrue(expected.containsAll(result))
    }


    @Test
    fun findMSB_correct() {
        val expected = 0b100000
        val input = 0b101011
        assertEquals(expected, findMSB(input))
    }

    @Test
    fun findMSB_correctOnOne() {
        val expected = 0b1
        val input = 0b1
        assertEquals(expected, findMSB(input))
    }

    @Test
    fun findMaxAndInArray_correct() {
        val expected = 0b1000
        val input = intArrayOf(4,8,12,16)
        assertEquals(expected, findMaximumANDInArray(input))
    }

    @Test
    fun posOfRightmostDifBit_correct_on_52_4() {
        val expected = 5
        assertEquals(expected, positionOfRightmostDifferentBit(52, 4))
    }

    @Test
    fun posOfRightmostDifBit_correct_on_11_9() {
        val expected = 2
        assertEquals(expected, positionOfRightmostDifferentBit(11, 9))
    }

    @Test
    fun posOfRightmostDifBit_correct_on_same_number() {
        val expected = -1
        assertEquals(expected, positionOfRightmostDifferentBit(11, 11))
    }

    @Test
    fun countAllSetBitsUntilNumber_correct() {
        val expected = 35
        assertEquals(expected, countSetBitsInAllNumbersUntil(17))
    }

    @Test
    fun countBitsToFlip_identical_correct() {
        val expected = 0
        assertEquals(expected, countBitToFlip(2, 2))
    }

    @Test
    fun countBitsToFlip_different_correct() {
        val expected = 3
        assertEquals(expected, countBitToFlip(25, 20))
    }

    @Test
    fun isSparse_true() {
        assertTrue(isSparse(2))
    }

    @Test
    fun isSparse_false() {
        assertFalse(isSparse(3))
    }

    @Test
    fun findMaxConsecutiveOnes_correctOnZero() {
        val expected = 0
        assertEquals(expected, findMaxConsecutiveOnes(0))
    }

    @Test
    fun findMaxConsecutiveOnes_correctOnAllOnes() {
        val expected = 4
        assertEquals(expected, findMaxConsecutiveOnes(15))
    }

    @Test
    fun findMaxConsecutiveOnes_correctOnMixed() {
        val expected = 4
        assertEquals(expected, findMaxConsecutiveOnes(222))
    }
}


















