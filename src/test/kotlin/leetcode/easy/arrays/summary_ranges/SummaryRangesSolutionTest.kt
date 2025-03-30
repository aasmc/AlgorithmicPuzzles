package leetcode.easy.arrays.summary_ranges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SummaryRangesSolutionTest {

    private val sut = SummaryRangesSolution()

    @Test
    fun testCorrect() {
        val expected = listOf("0->2", "4->5", "7")
        assertEquals(expected, sut.summaryRanges(intArrayOf(0,1,2,4,5,7)))

        val expectd2 = listOf("0", "2->4", "6", "8->9")
        assertEquals(expectd2, sut.summaryRanges(intArrayOf(0,2,3,4,6,8,9)))
    }

}