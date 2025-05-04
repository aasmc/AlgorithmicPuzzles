package leetcode.top_interview_150.linked_list.remove_nth_node_from_end

class RemoveNthNodeFromEndSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (n == 0 || head == null) {
            return head
        }
        val dummy = ListNode(0)
        dummy.next = head
        var fast = head
        var slow: ListNode? = dummy
        for (i in 0 until n) {
            fast = fast?.next
            if (fast == null) {
                break
            }
        }
        while (fast != null) {
            slow = slow?.next
            fast = fast.next
        }
        val tmp = slow?.next
        slow?.next = tmp?.next
        tmp?.next = null
        return dummy.next
    }

}