package geeks_for_geeks.algorithms.hashing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HashingLecturesTest {
    @Test
    fun countDistinctElementsCorrect() {
        assertEquals(4, countDistinctElementsIn(intArrayOf(15, 12, 12, 13, 16, 16, 15)))
        assertEquals(1, countDistinctElementsIn(intArrayOf(15, 15, 15, 15, 15, 15)))
        assertEquals(3, countDistinctElementsIn(intArrayOf(1, 2, 3)))
    }

    @Test
    fun findFrequenciesOfElementsCorrect() {
        val input = intArrayOf(10, 12, 15, 10, 15, 10, 20, 12, 12, 12)
        val expected = mapOf<Int, Int>(
            10 to 3,
            12 to 4,
            15 to 2,
            20 to 1
        )

        val result = findFrequenciesOfElements(input)
        assertEquals(expected, result)
    }

    @Test
    fun findIntersectionOfArraysCorrect() {
        val first = intArrayOf(10, 15, 20, 5, 30)
        val second = intArrayOf(30, 5, 30, 80)
        val expected = 2

        assertEquals(expected, findIntersectionOfArrays(first, second))

        val a = intArrayOf(10, 20)
        val b = intArrayOf(20, 30)
        val c = 1
        assertEquals(c, findIntersectionOfArrays(a, b))

        val aa = intArrayOf(10, 10, 10)
        val bb = intArrayOf(10, 10, 10)
        val cc = 1
        assertEquals(cc, findIntersectionOfArrays(aa, bb))
    }

    @Test
    fun findIntersectionOfArraysVersionWithSetsCorrect() {
        val first = intArrayOf(10, 15, 20, 5, 30)
        val second = intArrayOf(30, 5, 30, 80)
        val expected = 2

        assertEquals(expected, findIntersectionOfArraysVersionWithSets(first, second))

        val a = intArrayOf(10, 20)
        val b = intArrayOf(20, 30)
        val c = 1
        assertEquals(c, findIntersectionOfArraysVersionWithSets(a, b))

        val aa = intArrayOf(10, 10, 10)
        val bb = intArrayOf(10, 10, 10)
        val cc = 1
        assertEquals(cc, findIntersectionOfArraysVersionWithSets(aa, bb))
    }

    @Test
    fun countDistinctElementsInArraysCorrect() {
        val a = intArrayOf(15, 20, 5, 15)
        val b = intArrayOf(15, 15, 15, 20, 10)
        assertEquals(4, countDistinctElementsInArrays(a, b))

        val aa = intArrayOf(10, 12, 15)
        val bb = intArrayOf(18, 12)
        assertEquals(4, countDistinctElementsInArrays(aa, bb))

        val aaa = intArrayOf(3, 3, 3)
        val bbb = intArrayOf(3, 3)
        assertEquals(1, countDistinctElementsInArrays(aaa, bbb))
    }

    @Test
    fun countDistinctElementsInArraysKotlinStyleCorrect() {
        val a = intArrayOf(15, 20, 5, 15)
        val b = intArrayOf(15, 15, 15, 20, 10)
        assertEquals(4, countDistinctElementsKotlinStyle(a, b))

        val aa = intArrayOf(10, 12, 15)
        val bb = intArrayOf(18, 12)
        assertEquals(4, countDistinctElementsKotlinStyle(aa, bb))

        val aaa = intArrayOf(3, 3, 3)
        val bbb = intArrayOf(3, 3)
        assertEquals(1, countDistinctElementsKotlinStyle(aaa, bbb))
    }

    @Test
    fun findPairWithGivenSum_Correct() {
        val a = intArrayOf(3, 2, 8, 15, -8)
        val s = 17
        assertTrue(findPairWithGivenSum(a, s))

        val aa = intArrayOf(2, 1, 6, 3)
        val ss = 6
        assertFalse(findPairWithGivenSum(aa, ss))

        val aaa = intArrayOf(5, 8, -3, 6)
        val sss = 3
        assertTrue(findPairWithGivenSum(aaa, sss))
    }

    @Test
    fun subArrayWithZeroSum_correct() {
        val a = intArrayOf(1, 4, 13, -3, -10, 5)
        assertTrue(subarrayWithZeroSum(a))

        val aa = intArrayOf(-1, 4, -3, 5, 1)
        assertTrue(subarrayWithZeroSum(aa))

        val aaa = intArrayOf(3, 1, -2, 5, 6)
        assertFalse(subarrayWithZeroSum(aaa))

        val aaaa = intArrayOf(5, 6, 0, 8)
        assertTrue(subarrayWithZeroSum(aaaa))
    }

    @Test
    fun subArrayWithGivenSum_correct() {
        val a = intArrayOf(5, 8, 6, 13, 3, -1)
        assertTrue(subarrayWithGivenSum(a, 22))

        val aa = intArrayOf(15, 2, 8, 10, -5, -8, 6)
        assertTrue(subarrayWithGivenSum(aa, 3))

        val aaa = intArrayOf(3, 2, 5, 6)
        assertTrue(subarrayWithGivenSum(aaa, 10))

        val aaaa = intArrayOf(5,8,6,13)
        assertTrue(subarrayWithGivenSum(aaaa, 14))
    }
}
















