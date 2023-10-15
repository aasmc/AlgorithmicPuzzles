package leetcode.ace_coding.linked_list.reverse_list

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReverseSolutionTest {

    private lateinit var list: ReverseSolution.ListNode
    private val sut = ReverseSolution()

    @BeforeEach
    fun buildList() {
        list = ReverseSolution.ListNode(1)
        list.next = ReverseSolution.ListNode(2)
        list.next?.next = ReverseSolution.ListNode(3)
        list.next?.next?.next = ReverseSolution.ListNode(4)
        list.next?.next?.next?.next = ReverseSolution.ListNode(5)
    }

    @Test
    fun testReverseList() {
        val result = sut.reverseList(list)
        assertEquals(5, result?.`val` )
        assertEquals(4, result?.next?.`val` )
        assertEquals(3, result?.next?.next?.`val` )
        assertEquals(2, result?.next?.next?.next?.`val` )
        assertEquals(1, result?.next?.next?.next?.next?.`val` )
    }

}