package leetcode.top_interview_150.array_string.gas_stations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GasStationsSolutionTest {

    private val sut = GasStationsSolution()

    @Test
    fun testCorrect() {
        assertEquals(3, sut.canCompleteCircuit(intArrayOf(1,2,3,4,5), intArrayOf(3,4,5,1,2)))
        assertEquals(-1, sut.canCompleteCircuit(intArrayOf(2,3,4), intArrayOf(3,4,3)))
    }

}