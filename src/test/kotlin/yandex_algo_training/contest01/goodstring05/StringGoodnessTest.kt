package yandex_algo_training.contest01.goodstring05

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import yandex_algo_training.year_2022.contest01.goodstring05.findMaxGoodness

internal class StringGoodnessTest {
    @Test
    fun first_correct() {
        val ex = 2L
        val res = findMaxGoodness(3, listOf(1, 1, 1))
        assertEquals(ex, res)
    }

    @Test
    fun second_correct() {
        val ex = 3L
        val res = findMaxGoodness(2, listOf(3, 4))
        assertEquals(ex, res)
    }

    @Test
    fun third_correct() {
        val ex = 7L
        val res = findMaxGoodness(3, listOf(3,4,5))
        assertEquals(ex, res)
    }

    @Test
    fun fourth_correct() {
        val ex = 5L
        val res = findMaxGoodness(5, listOf(3,1,1,2,7))
        assertEquals(ex, res)
    }

    @Test
    fun test_41() {
        val res = findMaxGoodness(4, listOf(
            1_000_000_000,
            1_000_000_000,
            1_000_000_000,
            1_000_000_000,
        ))
        println(res)
        assertEquals(3_000_000_000, res)
    }
}