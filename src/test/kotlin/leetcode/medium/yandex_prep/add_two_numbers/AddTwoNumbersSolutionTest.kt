package leetcode.medium.yandex_prep.add_two_numbers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddTwoNumbersSolutionTest {

    private val sut = AddTwoNumbersSolution()

    @Test
    fun testCorrect() {

        val l = AddTwoNumbersSolution.ListNode(9)
        l.next = AddTwoNumbersSolution.ListNode(1)
        l.next!!.next = AddTwoNumbersSolution.ListNode(6)


        val r = AddTwoNumbersSolution.ListNode(0)

        val res = sut.addTwoNumbers(l, r)

        assertTrue(res != null)
    }

}