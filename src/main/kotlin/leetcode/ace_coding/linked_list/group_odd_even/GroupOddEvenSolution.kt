package leetcode.ace_coding.linked_list.group_odd_even

class GroupOddEvenSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun oddEvenList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        // tracks odd nodes
        var odd = head
        // head of even nodes
        val even = head.next
        // tracks even nodes
        var tmp = even
        while (odd?.next?.next != null) {
            // save odd node as the next node of the current odd node
            odd.next = odd.next?.next
            // save even node as the next node of the current even node
            tmp?.next = tmp?.next?.next
            // move current even node to the next even node
            tmp = tmp?.next
            // move current odd node to the next odd node
            odd = odd.next
        }
        odd?.next = even

        return head
    }

}