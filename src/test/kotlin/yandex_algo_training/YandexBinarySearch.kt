package yandex_algo_training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class YandexBinarySearch {

    @Test
    fun findNumberOfParents_correct() {
        val expected = 4
        val result = findNumberOfParents(1, 10)
        assertEquals(expected, result)
    }

    @Test
    fun findNumberOfDaysForInterview_correct() {
        val expected = 3
        val result = findNumberOfDaysForInterview(10, 3)
        assertEquals(expected, result)
    }

    @Test
    fun findFirstGreaterOrEqual_correct() {
        val expected = 5
        val result = findFirstGreaterOrEqual(listOf(0,1,2,3,4), 10)
        assertEquals(expected, result)

        val lst = listOf(1,2,3,4,5,5,6,6,6,6,6,6,8,9,10)
        val ex = 6
        val res = findFirstGreaterOrEqual(lst, 6)
        assertEquals(ex, res)

        val ex2 = 12
        val res2 = findFirstGreaterOrEqual(lst, 7)
        assertEquals(ex2, res2)
    }

    @Test
    fun countNumberOfX_correct() {
        val lst = listOf(1,2,3,4,5,5,6,6,6,6,6,6,8,9,10)
        val ex = 6
        val res = countNumberOfX(lst, 6)
        assertEquals(ex, res)

        val ex2 = 0
        val res2 = countNumberOfX(lst, 11)
        assertEquals(ex2, res2)

        val ex3 = 1
        val res3 = countNumberOfX(lst, 10)
        assertEquals(ex3, res3)
    }
}
