package leetcode.medium.yandex_prep.remove_nth_node

class RemoveNthNodeSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        val dummy = ListNode(0)
        dummy.next = head
        var left: ListNode? = dummy
        var right = head
        repeat(n) {
            right = right?.next
        }
        while (right != null) {
            left = left?.next
            right = right?.next
        }
        left?.next = left?.next?.next
        return dummy.next
    }

    fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        var current = head
        var size = 1
        while (current!!.next != null) {
            current = current.next
            ++size
        }
        val beforeRemoved = size - n
        if (beforeRemoved == 0) {
            return head.next
        }
        current = head
        var i = 1
        while (i != beforeRemoved) {
            current = current!!.next
            ++i
        }
        current!!.next = current.next?.next
        return head
    }
}