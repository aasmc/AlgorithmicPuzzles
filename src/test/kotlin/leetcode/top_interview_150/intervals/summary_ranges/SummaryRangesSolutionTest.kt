package leetcode.top_interview_150.intervals.summary_ranges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SummaryRangesSolutionTest {

    private val sut = SummaryRangesSolution()
    @Test
    fun testCorrect() {
//
//        val ex1 = listOf("0->2","4->5","7")
//        assertEquals(ex1, sut.summaryRanges(intArrayOf(0,1,2,4,5,7)))

        val ex2 = listOf("0","2->4","6","8->9")
        assertEquals(ex2, sut.summaryRanges(intArrayOf(0,2,3,4,6,8,9)))

    }

}