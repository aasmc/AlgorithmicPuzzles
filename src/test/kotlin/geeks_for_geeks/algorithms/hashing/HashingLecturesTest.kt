package geeks_for_geeks.algorithms.hashing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HashingLecturesTest {
    @Test
    fun countDistinctElementsCorrect() {
        assertEquals(4, countDistinctElementsIn(intArrayOf(15,12,12,13,16,16,15)))
        assertEquals(1, countDistinctElementsIn(intArrayOf(15,15,15,15,15,15)))
        assertEquals(3, countDistinctElementsIn(intArrayOf(1,2,3)))
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
}