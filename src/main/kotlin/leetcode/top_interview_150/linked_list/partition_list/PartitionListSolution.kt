package leetcode.top_interview_150.linked_list.partition_list

class PartitionListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
        override fun toString(): String = "ListNode(val=${`val`})"
    }

    fun partition(head: ListNode?, x: Int): ListNode? {
        var left: ListNode? = ListNode(0)
        val leftHead = left
        var right: ListNode? = ListNode(0)
        val rightHead = right
        var cur = head
        while (cur != null) {
            if (cur.`val` < x) {
                left?.next = ListNode(cur.`val`)
                left = left?.next
            } else {
                right?.next = ListNode(cur.`val`)
                right = right?.next
            }
            cur = cur.next
        }
        left?.next = rightHead?.next
        return leftHead?.next
    }

}