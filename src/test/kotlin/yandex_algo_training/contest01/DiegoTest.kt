package yandex_algo_training.contest01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import yandex_algo_training.year_2022.contest01.diego03.countNeededStickers

internal class DiegoTest {

    @Test
    fun diego_collectionaire_empty_correct() {
        val diegoList = listOf(0, 3, 5, 6)
        val colList = emptyList<Int>()
        val expected = emptyList<Int>()
        val result = countNeededStickers(colList, diegoList)
        assertEquals(expected, result)
    }

    @Test
    fun diego_empty_correct() {
        val diegoList = emptyList<Int>()
        val colList = listOf(4, 6)
        val expected = listOf(0, 0)
        val result = countNeededStickers(colList, diegoList)
        assertEquals(expected, result)
    }

    @Test
    fun diego_first_correct() {
        val diegoList = listOf(5)
        val colList = listOf(4, 6)
        val expected = listOf(0, 1)
        val result = countNeededStickers(colList, diegoList)
        assertEquals(expected, result)
    }

    @Test
    fun diego_second_correct() {
        val diegoList = listOf(1, 50, 100)
        val colList = listOf(300, 0, 75)
        val expected = listOf(3, 0, 2)
        val result = countNeededStickers(colList, diegoList)
        assertEquals(expected, result)
    }

    @Test
    fun test_five() {
        val diegoList = listOf<Int>(1,2,3,4)
        val collList = listOf(1,2,3,4,5)
        val expected = listOf(0,1,2,3,4)
        val result = countNeededStickers(collList, diegoList)
        assertEquals(expected, result)
    }
}