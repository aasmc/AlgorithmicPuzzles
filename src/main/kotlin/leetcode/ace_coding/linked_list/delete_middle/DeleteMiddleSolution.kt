package leetcode.ace_coding.linked_list.delete_middle

class DeleteMiddleSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun deleteMiddle(head: ListNode?): ListNode? {
        if (head == null) return null
        var n = 0
        var start = head
        while (start != null) {
            start = start.next
            ++n
        }
        if (n == 1) {
            return null
        }

        val mid = n / 2
        var second = head
        repeat(mid) {
            start = second
            second = second?.next
        }
        start?.next = second?.next
        return head
    }

}