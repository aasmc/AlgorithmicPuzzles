package leetcode.top_interview_150.divide_and_conquer.sort_list

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SortListSolutionTest {

    private val sut = SortListSolution()

    @Test
    fun testCorrect() {
        val head = SortListSolution.ListNode(4)
        head.next = SortListSolution.ListNode(2)
        head.next!!.next = SortListSolution.ListNode(1)
        head.next!!.next!!.next = SortListSolution.ListNode(3)
        val sorted = sut.sortList(head)
        assertEquals(sorted!!.`val`, 1)
    }

}