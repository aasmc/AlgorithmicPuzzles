package leetcode.ace_coding.linked_list.reverse_list

class ReverseSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var tmp: ListNode?
        var current = head
        var next = head.next
        current.next = null
        while (next != null) {
            tmp = next.next
            next.next = current
            current = next
            next = tmp
        }
        return current
    }

}