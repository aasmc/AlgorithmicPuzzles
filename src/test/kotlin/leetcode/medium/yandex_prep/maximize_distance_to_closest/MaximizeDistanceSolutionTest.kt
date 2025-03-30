package leetcode.medium.yandex_prep.maximize_distance_to_closest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaximizeDistanceSolutionTest {

    private val sut = MaximizeDistanceSolution()

    @Test
    fun testCorrect() {
        assertEquals(2, sut.maxDistToClosest(intArrayOf(1,0,0,0,1,0,1)))
        assertEquals(3, sut.maxDistToClosest(intArrayOf(1,0,0,0)))
        assertEquals(3, sut.maxDistToClosest(intArrayOf(0,0,0,1)))
        assertEquals(1, sut.maxDistToClosest(intArrayOf(1,0)))
        assertEquals(1, sut.maxDistToClosest(intArrayOf(0,1)))
    }

}