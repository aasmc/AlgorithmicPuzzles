package yandex_algo_training.contest01.control04

import org.junit.jupiter.api.Test
import yandex_algo_training.year_2022.contest01.control04.findPlaceForVasya
import kotlin.test.assertEquals

internal class ControlWorkTest {

    @Test
    fun control_first_correct() {
        val res = findPlaceForVasya(25, 2, 1, 2)
        assertEquals(res.first, 2)
        assertEquals(res.second, 2)
    }

    @Test
    fun control_second_correct() {
        val res = findPlaceForVasya(25, 13, 7, 1)
        assertEquals(res.first, -1)
        assertEquals(res.second, -1)
    }

    @Test
    fun controlWork_correct() {
        val res = findPlaceForVasya(12, 3, 4, 2)
        assertEquals(res.first, 3)
        assertEquals(res.second, 1)
    }

    @Test
    fun control_work_two_seats_two_variants() {
        val res = findPlaceForVasya(2, 2, 1, 1)
        assertEquals(res.first, -1)
        assertEquals(res.second, -1)
    }

    @Test
    fun six_seats_three_variants() {
        val res = findPlaceForVasya(6, 3, 2, 1)
        assertEquals(res.first, 3)
        assertEquals(res.second, 2)
    }

    @Test
    fun control_test_sixteen() {
        val res = findPlaceForVasya(987654321, 654321087, 123456789, 1)
        assertEquals(res.first, 450617332)
        assertEquals(res.second, 2)
    }
}