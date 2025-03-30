package leetcode.ace_coding.linked_list.max_twin_sum

import geeks_for_geeks.algorithms.sorting.heapSort

class TwinSumSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun pairSum(head: ListNode?): Int {
        var cur = head
        var n = 0
        while (cur != null) {
            cur = cur.next
            ++n
        }
        cur = head
        val mid = n / 2
        repeat(mid) {
            cur = cur?.next
        }
        var midHead = reverse(cur)
        cur = head
        var max = Int.MIN_VALUE
        while (midHead != null) {
            max = maxOf(max, cur!!.`val` + midHead.`val`)
            cur = cur?.next
            midHead = midHead.next
        }
        return max
    }

    private fun reverse(node: ListNode?): ListNode? {
        var tmp: ListNode?
        var current = node
        var next = node?.next
        current?.next = null
        while (next != null) {
            tmp = next.next
            next.next = current
            current = next
            next = tmp
        }
        return current
    }

}