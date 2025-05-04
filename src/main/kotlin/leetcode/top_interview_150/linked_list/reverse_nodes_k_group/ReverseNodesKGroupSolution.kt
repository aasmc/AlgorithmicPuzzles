package leetcode.top_interview_150.linked_list.reverse_nodes_k_group

class ReverseNodesKGroupSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
        override fun toString(): String = "ListNode(val=${`val`})"
    }

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) return head
        if (k == 1) return head
        var current = head
        var iterator = head
        var count = 1
        var newHead: ListNode? = null
        var tempTail: ListNode? = null
        while (iterator != null && count <= k) {
            iterator = iterator.next
            ++count
            if (count == k && iterator != null) {
                val reversed = reverseBetween(current, 1, k)
                if (newHead == null) {
                    newHead = reversed.first
                    tempTail = reversed.third
                } else {
                    tempTail?.next = reversed.first
                    tempTail = reversed.third
                }
                current = reversed.second
                iterator = reversed.second
                count = 1
            }
        }
        return newHead
    }

    private fun reverseBetween(head: ListNode?, left: Int, right: Int): Triple<ListNode?, ListNode?, ListNode?> {
        if (head == null) return Triple(head ,null , null)
        if (left == right) return Triple(head ,null , null)
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
        return Triple(newHead, nodeAfterReverseEnd, reverseRangeTailNode)
    }

    private fun onNodeBeforeReverseStartRange(count: Int, left: Int): Boolean =
        count == left - 1

}