package leetcode.top_interview_150.linked_list.reverse_linked_list_ii

class ReverseLinkedListTwoSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
        override fun toString(): String = "ListNode(val=${`val`})"
    }

    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null) return head
        if (left == right) return head
        var iterator = head
        var count = 1
        var nodeBeforeReverseStart: ListNode? = null
        while (iterator?.next != null && count < left) {
            if (onNodeBeforeReverseStartRange(count, left)) {
                nodeBeforeReverseStart = iterator
            }
            iterator = iterator.next
            ++count
        }
        val reverseRangeTailNode = iterator
        var current = iterator?.next
        ++count
        var nodeAfterReverseEnd: ListNode? = null
        while (current != null && count <= right) {
            val tmp = current.next
            current.next = iterator
            iterator = current
            if (count < right) {
                current = tmp
            }
            if (count == right) {
                nodeAfterReverseEnd = tmp
            }
            ++count
        }
        var newHead = head
        if (nodeBeforeReverseStart == null) {
            newHead = current
        } else {
            nodeBeforeReverseStart.next = current
        }
        reverseRangeTailNode?.next = nodeAfterReverseEnd
        return newHead
    }

    private fun onNodeBeforeReverseStartRange(count: Int, left: Int): Boolean =
        count == left - 1

}