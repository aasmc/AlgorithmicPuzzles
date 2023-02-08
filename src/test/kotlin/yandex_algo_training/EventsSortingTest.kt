package yandex_algo_training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EventsSortingTest {
    @Test
    fun maxVisitorsOnline_correct() {
        val tin = intArrayOf(1, 5, 7, 8)
        val tout = intArrayOf(6, 12, 8, 15)
        val n = 4
        val ex = 3
        val res = maxVisitorsOnline(n, tin, tout)
        assertEquals(ex, res)
    }

    @Test
    fun totalTimePresent_correct() {
        val tin = intArrayOf(1, 5, 7, 10)
        val tout = intArrayOf(3, 7, 8, 15)
        val n = 4
        val ex = 10
        val res = totalTimePresent(n, tin, tout)
        assertEquals(ex, res)
    }

    @Test
    fun bossOnlineTracker_correct() {
        val tin = intArrayOf(1,2,3,7,8)
        val tout = intArrayOf(3,5,5,9,11)
        val boss = intArrayOf(2,3,6,9,11)
        val ex = listOf(2,3,0,2,1)
        val res = bossOnlineTracker(5, tin, tout, 5, boss)
        assertEquals(ex, res)
    }
}