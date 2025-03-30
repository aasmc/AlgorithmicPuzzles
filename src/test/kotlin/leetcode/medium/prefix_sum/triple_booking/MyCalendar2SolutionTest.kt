package leetcode.medium.prefix_sum.triple_booking

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyCalendar2SolutionTest {

    private val sut = MyCalendar2Solution.MyCalendarTwo()

    @Test
    fun testCorrect() {
        assertTrue(sut.book(10, 20))
        assertTrue(sut.book(50, 60))
        assertTrue(sut.book(10, 40))
        assertFalse(sut.book(5, 15))
        assertTrue(sut.book(5, 10))
        assertTrue(sut.book(25, 55))
    }

}