package leetcode.top_interview_150.linked_list.rotate_list

class RotateListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
        override fun toString(): String = "ListNode(val=${`val`})"
    }

    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null
        if (k == 0) return head
        val (count, tail) = countAndGetTail(head)
        val rotateCount = k % count
        if (rotateCount == count || rotateCount == 0 || count == 1) {
            return head
        }
        var current = head
        var i = 0
        while (i < count - rotateCount - 1) {
            current = current?.next
            ++i
        }
        val newHead = current?.next
        current?.next = null
        tail?.next = head
        return newHead
    }

    private fun countAndGetTail(head: ListNode): Pair<Int, ListNode?> {
        var cur: ListNode? = head
        var count = 1
        while (cur?.next != null) {
            ++count
            cur = cur.next
        }
        return count to cur
    }

    fun rotateRight2(head: ListNode?, k: Int): ListNode? {
        if (k == 0) return head
        if (head == null) return head
        val count = count(head)
        if (count == 1) {
            return head
        }
        var newList = head
        repeat(k % count) {
            newList = rotateByOne(newList)
        }
        return newList
    }

    private fun count(head: ListNode): Int {
        var cur: ListNode? = head
        var count = 0
        while (cur != null) {
            ++count
            cur = cur.next
        }
        return count
    }

    private fun rotateByOne(head: ListNode?): ListNode? {
        var beforeTail: ListNode? = head
        while (beforeTail?.next?.next != null) {
            beforeTail = beforeTail.next
        }
        val newHead = beforeTail?.next
        newHead?.next = head
        beforeTail?.next = null
        return newHead
    }

}